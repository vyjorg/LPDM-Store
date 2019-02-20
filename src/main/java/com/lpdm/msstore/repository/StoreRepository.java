package com.lpdm.msstore.repository;

import com.lpdm.msstore.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store> findAllByNameContainingIgnoreCase(String name);
}
