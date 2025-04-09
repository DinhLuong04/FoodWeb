package com.DinhLuong.FoodDelivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DinhLuong.FoodDelivery.entity.Orders;
@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

     @Query(value = """
        SELECT 
            o.id AS orderId, o.create_date AS createDate, CAST( o.total_price AS DOUBLE) AS totalPrice, o.status AS status,
            r.title AS restaurant,
            a.full_address AS fullAddress, a.city AS city, a.district AS district, a.ward AS ward, a.phone AS phone,
            f.id AS foodId, f.title AS foodName, ot.quantity AS quantity,CAST(f.price AS DOUBLE) AS unitPrice
        FROM Orders o
        JOIN OrderDetail ot ON o.id = ot.order_id
        JOIN Food f ON ot.food_id = f.id
        JOIN Restaurant r ON o.res_id = r.id
        JOIN Address a ON o.address_id = a.id
        WHERE o.user_id = :userId
        ORDER BY o.create_date DESC;
        """, nativeQuery = true)
    List<Object[]> getOrdersByUserId(@Param("userId") int userId);

    @Query("SELECT DISTINCT o FROM Orders o " +
           "JOIN FETCH o.ListorderDetails od " +
           "JOIN FETCH od.food " +
           "JOIN FETCH o.restaurant " +
           "JOIN FETCH o.address " +
           "WHERE o.id = :orderId")
    Optional<Orders> findOrderWithDetails(@Param("orderId") Integer orderId);

    @Query("SELECT DISTINCT o FROM Orders o " +
    "JOIN FETCH o.ListorderDetails od " +
    "JOIN FETCH od.food " +
    "JOIN FETCH o.restaurant " +
    "JOIN FETCH o.address " +
    "WHERE o.status = 'processing' ")
    List<Orders> findAvailableOrdersForShippers();
}
