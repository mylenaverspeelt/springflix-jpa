package com.projeto.springflixjpa.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}