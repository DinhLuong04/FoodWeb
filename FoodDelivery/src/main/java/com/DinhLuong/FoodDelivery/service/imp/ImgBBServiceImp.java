package com.DinhLuong.FoodDelivery.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface ImgBBServiceImp {
    String upLoadImage(MultipartFile file);
    String findImageOnImgBB(String base64Image);
    boolean isSameImage(String imageUrl, String newImageBase64);
    boolean deleteImage(String imageUrl);
}
