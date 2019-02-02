package com.lpdm.msstore.exception;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(){

        super("No store was found");
    }
}
