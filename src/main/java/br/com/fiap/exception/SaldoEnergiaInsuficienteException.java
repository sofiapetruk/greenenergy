package br.com.fiap.exception;

public class SaldoEnergiaInsuficienteException extends RuntimeException{

    public SaldoEnergiaInsuficienteException(String message) {
        super(message);
    }

    public SaldoEnergiaInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

}
