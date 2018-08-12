package com.mgrsys.authorization.authorize.model.exception;

/**
 * Developed by Magora Team (magora-systems.com)
 * 2017
 *
 * @author Viktor Zemtsov
 */
public class NetworkUnavailableException extends RuntimeException {
    private static final String ERROR = "Network connection is unavailable";

    public NetworkUnavailableException() {
        super(ERROR);
    }
}
