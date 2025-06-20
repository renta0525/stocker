package com.example.stocker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stocker.domain.Stock;
import com.example.stocker.repository.StockRepository;

@Service
public class StockService {
    
    @Autowired
    private StockRepository repository;

    public List<Stock> findAll () {
        return repository.findAll();
    }
    public List<Stock> findByShopId (int shopId) {
        return repository.findByShopId(shopId);
    }

    public Optional<Stock> findById(int id) {
        return repository.findById(id);
    }

    public void updateByStock (Stock stock) {
        repository.updateByStock(stock);
    }

    public void insertStock(Stock stock) {
        repository.insertStock(stock);
    }
}
