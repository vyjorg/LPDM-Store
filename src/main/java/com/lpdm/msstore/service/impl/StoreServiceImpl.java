package com.lpdm.msstore.service.impl;

import com.lpdm.msstore.repository.StoreRepository;
import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Optional<Store> findStoreById(int id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoreByName(String name) {
        return storeRepository.findAllByNameContainingIgnoreCase(name);
    }
}
