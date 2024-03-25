package com.gabriel.CrudSpring.exception;


public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id){
        super("Não foi encontrado o curso com id " + id);
    }
}
