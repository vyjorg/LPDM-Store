package com.lpdm.msstore.service;

import com.lpdm.msstore.model.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    Optional<Store> findStoreById(int id);
    List<Store> findStoreByName(String name);
}
