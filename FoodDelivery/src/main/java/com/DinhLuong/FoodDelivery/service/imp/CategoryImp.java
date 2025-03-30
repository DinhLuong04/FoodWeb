package com.DinhLuong.FoodDelivery.service.imp;

import java.util.List;

import com.DinhLuong.FoodDelivery.dto.CategoryDTO;

public interface CategoryImp {
    List<CategoryDTO> getAll();
    CategoryDTO getCate(int id);
    CategoryDTO addCate(String nameCate);
    CategoryDTO update(int id,String nameCate);
    boolean DeleteCate(int id);
}
