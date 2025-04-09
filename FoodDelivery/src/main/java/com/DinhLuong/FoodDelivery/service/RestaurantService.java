package com.DinhLuong.FoodDelivery.service;

import java.io.IOException;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.CategoryDTO;
import com.DinhLuong.FoodDelivery.dto.FoodDTO;
import com.DinhLuong.FoodDelivery.dto.RatingforResDTO;
import com.DinhLuong.FoodDelivery.dto.RestaurantDTO;
import com.DinhLuong.FoodDelivery.entity.Category;
import com.DinhLuong.FoodDelivery.entity.Food;
import com.DinhLuong.FoodDelivery.entity.MenuRestaurant;
import com.DinhLuong.FoodDelivery.entity.RatingRestaurant;
import com.DinhLuong.FoodDelivery.entity.Restaurant;
import com.DinhLuong.FoodDelivery.entity.keys.KeyMenuRestaurant;
import com.DinhLuong.FoodDelivery.payload.respone.OrdersRepone;
import com.DinhLuong.FoodDelivery.repository.CategoryRepository;
import com.DinhLuong.FoodDelivery.repository.MenuResRepository;
import com.DinhLuong.FoodDelivery.repository.RatingRestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.service.imp.RestaurantImp;

@Service
public class RestaurantService implements RestaurantImp {

    @Autowired
    RestaurantRepository Resrepository;
    @Autowired
    ImgBBService imgBBService;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MenuResRepository menuResRepository;
    @Autowired
    RatingRestaurantRepository ratingRestaurantRepository;

    public String checkAndUploadImage(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Mã hóa ảnh mới sang Base64
                String newImageBase64 = Base64.getEncoder().encodeToString(file.getBytes());

                // Kiểm tra ảnh đã tồn tại trên ImgBB chưa
                String existingImageUrl = imgBBService.findImageOnImgBB(newImageBase64);
                
                if (existingImageUrl != null) {
                    return existingImageUrl; 
                } else {
                    return imgBBService.upLoadImage(file); 
                }
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi đọc ảnh: " + e.getMessage());
            }
        }
        return null; 
    }

    @Override
    public List<RestaurantDTO> getAllRes() {
        List<Restaurant> list = Resrepository.findAll();
        List<RestaurantDTO> listResDTO = new ArrayList<>();
        for (Restaurant res : list) {
            RestaurantDTO resDTO = new RestaurantDTO();
            resDTO.setId(res.getId());
            resDTO.setTitle(res.getTitle());
            resDTO.setSubtitle(res.getSubtitle());
            resDTO.setDescription(res.getDescription());
            resDTO.setImage(res.getImage());
            resDTO.setFreeship(res.isFreeship());
            resDTO.setAddress(res.getAddress());
            resDTO.setOpenDate(res.getOpenDate());
            resDTO.setRating(CaculatorRating(res.getListRatingRestaurant()));
            listResDTO.add(resDTO);
        }
        //     List<CategoryDTO> listCate = new ArrayList<>();
        //     for (MenuRestaurant menuRes : res.getListMenuRestaurant()) {
        //         CategoryDTO cateDTO = new CategoryDTO();
        //         cateDTO.setId(menuRes.getCategory().getId());
        //         cateDTO.setNameCate(menuRes.getCategory().getNameCate());
        //         cateDTO.setCreatDate(menuRes.getCategory().getCreatDate());
        //         List<FoodDTO> menuDTO = new ArrayList<>();
        //         for (Food data : menuRes.getCategory().getFoods()) {
        //             FoodDTO food = new FoodDTO();
        //             food.setId(data.getId());
        //             food.setTitle(data.getTitle());
        //             food.setImage(data.getImage());
        //             food.setTimeShip(data.getTimeShip());
        //             food.setPrice(data.getPrice());
        //             menuDTO.add(food);
        //         }
        //         cateDTO.setMenu(menuDTO);
        //         listCate.add(cateDTO);
        //     }
        //     resDTO.setCategory(listCate);
           
        // }
        return listResDTO;
    }

    // hàm tính số sao đánh giá
    private double CaculatorRating(Set<RatingRestaurant> listRatingRestaurant) {
        double totalPoin = 0;
        for (RatingRestaurant data : listRatingRestaurant) {
            totalPoin += data.getRatePoint();
        }
        return totalPoin / listRatingRestaurant.size();
    }

    @Override
    public RestaurantDTO getByID(int id) {
        Restaurant res = Resrepository.findById(id).orElseThrow(()-> new RuntimeException("Not found resID"+id));
        RestaurantDTO resDTO = new RestaurantDTO();
        resDTO.setId(res.getId());
        resDTO.setTitle(res.getTitle());
        resDTO.setSubtitle(res.getSubtitle());
        resDTO.setDescription(res.getDescription());
        resDTO.setImage(res.getImage());
        resDTO.setFreeship(res.isFreeship());
        resDTO.setAddress(res.getAddress());
        resDTO.setOpenDate(res.getOpenDate());
        resDTO.setRating(CaculatorRating(res.getListRatingRestaurant()));
        List<RatingforResDTO> ListRating=new ArrayList<>();
        for (RatingRestaurant rateRes : res.getListRatingRestaurant()) {
            RatingforResDTO rate=new RatingforResDTO();
            rate.setId(rateRes.getId());
            rate.setUserId(rateRes.getUsers().getId());
            rate.setUserName(rateRes.getUsers().getFullName());
            rate.setContent(rateRes.getContent());
            rate.setRatePoint(rateRes.getRatePoint());
            ListRating.add(rate);
        }

        List<CategoryDTO> listCate = new ArrayList<>();
        for (MenuRestaurant menuRes : res.getListMenuRestaurant()) {
            CategoryDTO cateDTO = new CategoryDTO();
            cateDTO.setId(menuRes.getCategory().getId());
            cateDTO.setNameCate(menuRes.getCategory().getNameCate());
            cateDTO.setCreatDate(menuRes.getCategory().getCreatDate());
            List<FoodDTO> menuDTO = new ArrayList<>();
            for (Food data : menuRes.getCategory().getFoods()) {
                FoodDTO food = new FoodDTO();
                food.setId(data.getId());
                food.setTitle(data.getTitle());
                food.setImage(data.getImage());
                food.setTimeShip(data.getTimeShip());
                food.setPrice(data.getPrice());
                menuDTO.add(food);
            }
            cateDTO.setMenu(menuDTO);
            listCate.add(cateDTO);
        }
        resDTO.setCategory(listCate);
        resDTO.setListRating(ListRating);
        return resDTO;
    }

    @Override
    public Restaurant addRestaurant(String title, String subtitle, String description, MultipartFile file,
            Boolean isFreeship, String address) {
                
        Date now = Date.from(Instant.now());
        Restaurant restaurant = new Restaurant();
        restaurant.setTitle(title);
        restaurant.setSubtitle(subtitle);
        restaurant.setDescription(description);
        restaurant.setFreeship(isFreeship);
        restaurant.setAddress(address);
        restaurant.setOpenDate(now);
        String imageUrl = checkAndUploadImage(file);
        if (imageUrl != null) {
            restaurant.setImage(imageUrl);
        }
        

        return Resrepository.save(restaurant);
    }

    @Override
    public String addCategory(int resID, int cateID) {
        Restaurant restaurant = Resrepository.findById(resID).orElseThrow(()->new RuntimeException("ResID not found +"+resID));
       
        Category category = categoryRepository.findById(cateID)
                .orElseThrow(() -> new RuntimeException("Category không tồn tại!"));

        KeyMenuRestaurant key = new KeyMenuRestaurant(cateID, resID);
        if (menuResRepository.existsById(key)) {
            throw new RuntimeException("Category đã tồn tại trong Restaurant này!");
        }

        MenuRestaurant menuRestaurant = new MenuRestaurant();
        menuRestaurant.setKeys(key);
        menuRestaurant.setCategory(category);
        menuRestaurant.setRestaurant(restaurant);
        Date now = Date.from(Instant.now());
        menuRestaurant.setCreateDate(now);
        menuResRepository.save(menuRestaurant);
        return "Success";
    }

    @Override
    public Restaurant Update(int Id, String title, String subtitle, String description, MultipartFile file,
            Boolean isFreeship, String address) {
        Restaurant res = Resrepository.findById(Id).orElseThrow(()->new RuntimeException("Not found ResID:  "+Id));
        if(res==null) new RuntimeException("Not found Res by id: " + Id);
        res.setTitle(title);
        res.setSubtitle(subtitle);
        res.setDescription(description);
        res.setFreeship(isFreeship);
        res.setAddress(address);
        String imageUrl = checkAndUploadImage(file);
        if (imageUrl != null) {
            res.setImage(imageUrl);
        }
        return Resrepository.save(res);
    }

    @Override
    public boolean deleteRes(int id) {
        Restaurant res = Resrepository.findById(id).orElseThrow(()->new RuntimeException("Not found ResID:  "+id));
        // // Kiểm tra xóa ảnh trên ImgBB
        // if (res.getImage() != null) {
        //     boolean deleted = imgBBService.deleteImage(res.getImage());
        //     if (!deleted) {
        //         throw new RuntimeException("Không thể xóa ảnh trên ImgBB, vui lòng thử lại!");
        //     }
        // }

        Resrepository.delete(res);
        return true;
    }

    @Override
    public List<OrdersRepone> getListOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListOrders'");
    }

}
