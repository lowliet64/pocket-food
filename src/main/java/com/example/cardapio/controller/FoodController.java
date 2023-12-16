package com.example.cardapio.controller;


import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FoodController {
    @Autowired
    private FoodRepository repository;
    @GetMapping
    public List<FoodResponseDTO> listAll(){
        List<FoodResponseDTO> foods = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foods;
    }

    @PostMapping
    public void create(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);

    }
}
