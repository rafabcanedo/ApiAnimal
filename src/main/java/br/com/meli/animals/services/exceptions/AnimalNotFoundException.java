package br.com.meli.animals.services.exceptions;

public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException(String message) {
        super(message);
    }

}
