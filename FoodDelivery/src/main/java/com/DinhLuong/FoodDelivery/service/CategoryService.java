package com.DinhLuong.FoodDelivery.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DinhLuong.FoodDelivery.dto.CategoryDTO;
import com.DinhLuong.FoodDelivery.entity.Category;
import com.DinhLuong.FoodDelivery.repository.CategoryRepository;
import com.DinhLuong.FoodDelivery.service.imp.CategoryImp;
@Service
public class CategoryService implements CategoryImp {
    private final Date now = Date.from(Instant.now());
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAll() {
        List<Category> list=categoryRepository.findAll();
        List<CategoryDTO> listDTO=new ArrayList<>();
        for(Category x : list){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setId(x.getId());
            categoryDTO.setNameCate(x.getNameCate());
            categoryDTO.setCreatDate(x.getCreatDate());
            listDTO.add(categoryDTO);
        }
       
        return listDTO;
    }

    @Override
    public CategoryDTO getCate(int id) 
    {
        Category category=categoryRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Not found Category ID: "+id));
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNameCate(category.getNameCate());
        categoryDTO.setCreatDate(category.getCreatDate());
        return categoryDTO;
    }

    @Override
    public CategoryDTO addCate(String nameCate) {
        Category category=new Category();
        category.setNameCate(nameCate);
        category.setCreatDate(now);
        categoryRepository.save(category);
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNameCate(category.getNameCate());
        categoryDTO.setCreatDate(category.getCreatDate());
        return categoryDTO;
    }

    @Override
    public CategoryDTO update(int id,String nameCate) {
        Category category=categoryRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Not found Category ID: "+id));
        category.setNameCate(nameCate);
        categoryRepository.save(category);
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNameCate(category.getNameCate());
        categoryDTO.setCreatDate(category.getCreatDate());
        return categoryDTO;
    }

    @Override
    public boolean DeleteCate(int id) {
        Category category=categoryRepository.findById(id)
        .orElseThrow(()->new RuntimeException("Not found Category ID: "+id));
        categoryRepository.delete(category);
        return true;
    }
    
}
