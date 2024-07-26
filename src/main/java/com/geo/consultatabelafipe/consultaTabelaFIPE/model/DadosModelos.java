package com.geo.consultatabelafipe.consultaTabelaFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(@JsonAlias("modelos") List<Dados> modelos) {
}
