package br.com.meli.animals.services.exceptions;

public class AnimalTypeNotFoundException extends RuntimeException {

    public AnimalTypeNotFoundException(String message) {
        super(message);
    }

}
