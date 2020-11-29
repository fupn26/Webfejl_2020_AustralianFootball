package hu.unideb.fupn26.exception;

import hu.unideb.fupn26.model.Match;

public class UnknownTeamException extends Exception {
    public UnknownTeamException(String message) {
        super(message);
    }
}
