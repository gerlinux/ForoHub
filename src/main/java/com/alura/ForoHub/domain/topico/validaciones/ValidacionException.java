package com.alura.ForoHub.domain.topico.validaciones;

public class ValidacionException extends RuntimeException{
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
