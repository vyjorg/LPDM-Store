package com.lpdm.msstore.service.impl;

import com.lpdm.msstore.exception.StoreNotFoundException;
import com.lpdm.msstore.model.location.Address;
import com.lpdm.msstore.proxy.LocationProxy;
import com.lpdm.msstore.repository.StoreRepository;
import com.lpdm.msstore.model.Store;
import com.lpdm.msstore.service.StoreService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

@Service
public class StoreServiceImpl implements StoreService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final StoreRepository storeRepository;
    private final LocationProxy locationProxy;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, LocationProxy locationProxy) {

        this.storeRepository = storeRepository;
        this.locationProxy = locationProxy;
    }

    /**
     * This method add an {@link Address} object to a {@link Store} object
     * @param store The {@link Store} object that need an {@link Address} object
     * @return The {@link Store} object with the {@link Address} if it was found
     */
    private Store addAddress(Store store){

        if(store.getAddressId() != 0){
            try { store.setAddress(locationProxy.findAddressById(store.getAddressId())); }
            catch (FeignException e) { log.warn(e.getMessage()); }
        }

        return store;
    }

    /**
     * Find a {@link Store} by its id
     * @param id The {@link Store} id
     * @return The {@link Store} object found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @Override
    public Store findStoreById(int id) throws StoreNotFoundException {

        Optional<Store> optStore = storeRepository.findById(id);
        if(!optStore.isPresent()) throw new StoreNotFoundException();

        return addAddress(optStore.get());
    }

    /**
     * Find a {@link Store} by its name
     * @param name The {@link Store} name
     * @return A {@link List} of {@link Store} objects found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @Override
    public List<Store> findStoreByName(String name) throws StoreNotFoundException {

        List<Store> storeList = storeRepository.findAllByNameContainingIgnoreCase(name);

        if(storeList.isEmpty())
            throw new StoreNotFoundException();

        storeList.forEach(this::addAddress);

        return storeList;
    }

    /**
     * Find all {@link Store} in the database
     * @return All {@link Store} found
     * @throws StoreNotFoundException Thrown if no {@link Store} was found
     */
    @Override
    public List<Store> findAllStores() throws StoreNotFoundException {

        List<Store> storeList = storeRepository.findAll();

        if(storeList.isEmpty())
            throw new StoreNotFoundException();

        storeList.forEach(this::addNewStore);

        return storeList;
    }

    /**
     * Persist a new {@link Store} object in the database
     * @param store The {@link Store} object to persist
     * @return The {@link Store} object persisted
     */
    @Override
    public Store addNewStore(Store store) {

        return storeRepository.save(store);
    }

    /**
     * Delete a {@link Store} object in the database
     * @param store The {@link Store} object to delete
     * @return True if the {@link Store} object was deleted, otherwise false
     * @throws StoreNotFoundException Thrown if the {@link Store} object to delete was not found
     */
    @Override
    public boolean deleteStore(Store store) throws StoreNotFoundException {

        Optional<Store> optStore = storeRepository.findById(store.getId());
        if(!optStore.isPresent()) throw new StoreNotFoundException();

        storeRepository.delete(optStore.get());

        optStore = storeRepository.findById(store.getId());

        return !optStore.isPresent();
    }

    /**
     * Update a {@link Store} object in the database
     * @param store The {@link Store} object to update
     * @return The {@link Store} object update
     * @throws StoreNotFoundException Thrown if the {@link Store} object was not found
     */
    @Override
    public Store updateStore(Store store) throws StoreNotFoundException {

        Optional<Store> optStore = storeRepository.findById(store.getId());
        if(!optStore.isPresent()) throw new StoreNotFoundException();

        return storeRepository.save(store);
    }
}
