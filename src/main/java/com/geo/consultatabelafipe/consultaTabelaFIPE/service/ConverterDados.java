package com.geo.consultatabelafipe.consultaTabelaFIPE.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class ConverterDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classes) {
        try{
            return mapper.readValue(json, classes);
        } catch (JsonProcessingException ex) {
            throw  new RuntimeException( ex)  ;
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        //Isso so foi usado por temos um tipo generico de dados
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json,lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
