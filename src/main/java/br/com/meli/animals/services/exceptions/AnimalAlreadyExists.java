package br.com.meli.animals.services.exceptions;

public class AnimalAlreadyExists extends RuntimeException {

    public AnimalAlreadyExists(String message) {
        super(message);
    }

}
