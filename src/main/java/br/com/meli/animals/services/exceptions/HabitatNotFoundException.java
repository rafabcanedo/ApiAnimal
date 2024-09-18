package br.com.meli.animals.services.exceptions;

public class HabitatNotFoundException extends RuntimeException {

    public HabitatNotFoundException(String message) {
        super(message);
    }

}
