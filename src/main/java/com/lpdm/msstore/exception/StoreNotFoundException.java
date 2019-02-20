package com.lpdm.msstore.exception;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(){

        super("No store was found");
    }
}
