package hu.unideb.fupn26.exception;

import hu.unideb.fupn26.model.Match;
import lombok.Getter;

@Getter
public class UnknownMatchException extends Exception {

    private Match match;

    public UnknownMatchException(String message, Match match) {
        super(message);
        this.match = match;
    }
}
