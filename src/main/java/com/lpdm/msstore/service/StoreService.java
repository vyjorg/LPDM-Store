package com.lpdm.msstore.service;

import com.lpdm.msstore.exception.StoreNotFoundException;
import com.lpdm.msstore.model.Store;

import java.util.List;

public interface StoreService {

    /**
     * Find a {@link Store} by its id
     * @param id The {@link Store} id
     * @return The {@link Store} object found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    Store findStoreById(int id) throws StoreNotFoundException;

    /**
     * Find a {@link Store} by its name
     * @param name The {@link Store} name
     * @return A {@link List} of {@link Store} objects found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    List<Store> findStoreByName(String name) throws StoreNotFoundException;

    /**
     * Find all {@link Store} in the database
     * @return All {@link Store} found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    List<Store> findAllStores() throws StoreNotFoundException;

    /**
     * Persist a new {@link Store} object in the database
     * @param store The {@link Store} object to persist
     * @return The {@link Store} object persisted
     */
    Store addNewStore(Store store);

    /**
     * Delete a {@link Store} object in the database
     * @param store The {@link Store} object to delete
     * @return True if the {@link Store} object was deleted, otherwise false
     * @throws StoreNotFoundException Thrown if the {@link Store} object to delete was not found
     */
    boolean deleteStore(Store store) throws StoreNotFoundException;

    /**
     * Update a {@link Store} object in the database
     * @param store The {@link Store} object to update
     * @return The {@link Store} object update
     * @throws StoreNotFoundException Thrown if the {@link Store} object was not found
     */
    Store updateStore(Store store) throws StoreNotFoundException;
}
