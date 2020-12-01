package hu.unideb.fupn26.exception;

import hu.unideb.fupn26.dao.entity.PlayerEntity;

public class UnknownPlayerException extends Exception {

    public UnknownPlayerException(String message) {
        super(message);
    }
}
