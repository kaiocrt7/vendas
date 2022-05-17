package com.udemy.api.vendas.domain.exception;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String mensagem){
        super(mensagem);
    }
}
