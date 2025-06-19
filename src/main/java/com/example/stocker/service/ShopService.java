package com.example.stocker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.Shop;
import com.example.stocker.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;
    
    public List<Shop> shopList () {
        return repository.shopList();
    }

    public List<Shop> shopListByUserId(int userId) {
        return repository.shopListByUserId(userId);
    }

    public Optional<Shop> findById (int id) {
        return repository.findById(id);
    }

    public void updateByShop (Shop shop) {
        repository.updateByShop(shop);
    }

    public void deleteByShop (int id) {
        repository.deleteByShop(id);
    }
}
