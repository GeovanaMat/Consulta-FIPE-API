package com.geo.consultatabelafipe.consultaTabelaFIPE.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> T);
    <T> List<T> obterLista(String json, Class<T> classe);
}
