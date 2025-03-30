package com.DinhLuong.FoodDelivery.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.dto.RatingRestaurantDTO;
import com.DinhLuong.FoodDelivery.dto.RestaurantDTO;
import com.DinhLuong.FoodDelivery.entity.Restaurant;

import com.DinhLuong.FoodDelivery.payload.responeData;
import com.DinhLuong.FoodDelivery.repository.RestaurantRepository;
import com.DinhLuong.FoodDelivery.repository.UserRepository;
import com.DinhLuong.FoodDelivery.service.RattingRestaurantService;
import com.DinhLuong.FoodDelivery.service.RestaurantService;

@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantService resService;
    @Autowired
    RattingRestaurantService rattingRestaurantService;

    // get full res
    @GetMapping("/getAllRes")
    public ResponseEntity<?> getAllRes() {
        responeData responeData = new responeData();
        responeData.setStatus(200);
        responeData.setMessage("success");
        List<RestaurantDTO> list = resService.getAllRes();
        responeData.setData(list);
        return new ResponseEntity<>(responeData, HttpStatus.OK);
    }

    // get res theo id
    @GetMapping("/getRes")
    public ResponseEntity<?> getAllRes(@RequestParam int id) {
        responeData responseData = new responeData();

        try {
            RestaurantDTO resDTO = resService.getByID(id);
            if (resDTO == null) {
                responseData.setStatus(404);
                responseData.setMessage("Không tìm thấy nhà hàng với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            responseData.setStatus(200);
            responseData.setMessage("Success");
            responseData.setData(resDTO);
            return ResponseEntity.ok(responseData);

        } catch (Exception e) {
            e.printStackTrace();
            responseData.setStatus(500);
            responseData.setMessage("Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    // add res
    @PostMapping("/add")
    public ResponseEntity<?> addRes(
            @RequestParam("title") String title,
            @RequestParam("subtitle") String subtitle,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam("isFreeship") Boolean isFreeship,
            @RequestParam("address") String address) {
        try {
            Restaurant newRes = resService.addRestaurant(title, subtitle, description, file, isFreeship, address);
            responeData responseData = new responeData();
            responseData.setStatus(200);
            responseData.setMessage("Success");
            responseData.setData(newRes);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi upload ảnh: " + e.getMessage());
        }

    }

    // Cập nhật res
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRestaurant(
            @PathVariable int id,
            @RequestParam("title") String title,
            @RequestParam("subtitle") String subtitle,
            @RequestParam("description") String description,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("isFreeship") Boolean isFreeship,
            @RequestParam("address") String address) {

        responeData responseData = new responeData();
        try {
            Restaurant updatedRes = resService.Update(id, title, subtitle, description, file, isFreeship, address);
            RestaurantDTO resDTO = new RestaurantDTO();
            resDTO.setId(updatedRes.getId());
            resDTO.setTitle(updatedRes.getTitle());
            resDTO.setSubtitle(updatedRes.getSubtitle());
            resDTO.setDescription(updatedRes.getDescription());
            resDTO.setImage(updatedRes.getImage());
            resDTO.setFreeship(updatedRes.isFreeship());
            resDTO.setAddress(updatedRes.getAddress());
            responseData.setStatus(200);
            responseData.setMessage("Cập nhật nhà hàng thành công!");
            responseData.setData(resDTO);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(500);
            responseData.setMessage("Lỗi khi cập nhật nhà hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    // Xóa nhà hàng theo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable int id) {
        responeData responseData = new responeData();
        try {
            boolean isDeleted = resService.deleteRes(id);
            if (!isDeleted) {
                responseData.setStatus(404);
                responseData.setMessage("Không tìm thấy nhà hàng với ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }
            responseData.setStatus(200);
            responseData.setMessage("Xóa nhà hàng thành công!");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setStatus(500);
            responseData.setMessage("Lỗi khi xóa nhà hàng: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    // Thêm cate cho res
    @PostMapping("/{restaurantId}/add-category/{categoryId}")
    public ResponseEntity<?> addCategory(@PathVariable int restaurantId,
            @PathVariable int categoryId) {
        responeData responeData = new responeData();
        try {
            String Message = resService.addCategory(restaurantId, categoryId);
            responeData.setMessage(Message);
            responeData.setStatus(200);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    //add rating for res
    @PostMapping("/{ResId}/add-Rating/{UserId}")
    public ResponseEntity<?> addRatting(
            @PathVariable int ResId,
            @PathVariable int UserId,
            @RequestParam String content,
            @RequestParam int ratePoint) {
        responeData responeData = new responeData();
        try {
            responeData.setStatus(200);
            responeData.setMessage("success");
            var data = rattingRestaurantService.addRate(UserId, ResId, content, ratePoint);
            RatingRestaurantDTO ratingRestaurantDTO = new RatingRestaurantDTO();
            ratingRestaurantDTO.setId(data.getId());
            ratingRestaurantDTO.setContent(data.getContent());
            ratingRestaurantDTO.setRatePoint(data.getRatePoint());
            ratingRestaurantDTO.setUserId(data.getUsers().getId());
            ratingRestaurantDTO.setUserName(data.getUsers().getFullName());
            ratingRestaurantDTO.setRestaurantId(data.getRestaurant().getId());
            ratingRestaurantDTO.setRestaurantName(data.getRestaurant().getTitle());
            responeData.setData(ratingRestaurantDTO);
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

}
