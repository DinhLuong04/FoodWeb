package com.DinhLuong.FoodDelivery.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.DinhLuong.FoodDelivery.service.imp.ImgBBServiceImp;
@Service
public class ImgBBService implements ImgBBServiceImp  {

    @Value("${imgbb.api.url}")
    private String imgbbUrl;

    @Value("${imgbb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String upLoadImage (MultipartFile file) {
        String encodedImage = null;
        try {
            encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("key", apiKey);
        body.add("image", encodedImage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(imgbbUrl, HttpMethod.POST, requestEntity, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map data = (Map) response.getBody().get("data");
            return (String) data.get("url");
        }
        throw new RuntimeException("Not upload  ImgBB");
    }

     // Phương thức kiểm tra ảnh có tồn tại trên ImgBB không
     @Override
     public String findImageOnImgBB(String newImageBase64) {
         try {
            
            // Gửi yêu cầu GET đến ImgBB để lấy danh sách ảnh
             String url = imgbbUrl + "?key=" + apiKey;
 
             ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
             if (response.getStatusCode().is2xxSuccessful()) {
                 Map data = (Map) response.getBody().get("data");
                 List<Map<String, Object>> images = (List<Map<String, Object>>) data.get("images");
 
                 for (Map<String, Object> image : images) {
                     String imageUrl = (String) image.get("url");
 
                     // Kiểm tra xem ảnh đã tồn tại hay chưa
                     if (isSameImage(imageUrl, newImageBase64)) {
                         return imageUrl; // Trả về URL ảnh đã có trên ImgBB
                     }
                 }
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null; // Ảnh chưa tồn tại trên ImgBB
     }
 
     // So sánh ảnh mới với ảnh đã có trên ImgBB
     @Override
     public boolean isSameImage(String imageUrl, String newImageBase64) {
         try {
            
             ResponseEntity<byte[]> imgResponse = restTemplate.getForEntity(imageUrl, byte[].class);
             if (imgResponse.getStatusCode().is2xxSuccessful()) {
                 String existingImageBase64 = Base64.getEncoder().encodeToString(imgResponse.getBody());
                 return existingImageBase64.equals(newImageBase64);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
     }

     @Override
     public boolean deleteImage(String imageUrl) {
         try {
             String imageId = extractImageId(imageUrl);
             if (imageId == null) {
                 return false;
             }
 
             String deleteUrl = "https://api.imgbb.com/1/image/" + imageId + "?key=" + apiKey;
             ResponseEntity<Map> response = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, Map.class);
 
             return response.getStatusCode().is2xxSuccessful();
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
 
     // 📌 Hàm trích xuất Image ID từ URL ảnh ImgBB
     private String extractImageId(String imageUrl) {
         if (imageUrl == null || !imageUrl.contains("/")) {
             return null;
         }
         String[] parts = imageUrl.split("/");
         return parts.length > 4 ? parts[parts.length - 1] : null;
     }
    
}
