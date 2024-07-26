package com.geo.consultatabelafipe.consultaTabelaFIPE.principal;

import com.geo.consultatabelafipe.consultaTabelaFIPE.model.Dados;

import com.geo.consultatabelafipe.consultaTabelaFIPE.model.DadosCarro;
import com.geo.consultatabelafipe.consultaTabelaFIPE.model.DadosModelos;
import com.geo.consultatabelafipe.consultaTabelaFIPE.service.ConsumirAPI;
import com.geo.consultatabelafipe.consultaTabelaFIPE.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final Scanner leitura = new Scanner(System.in);
    private  final String URL_BASE = "https://parallelum.com.br/fipe/api/v1";
    private  final ConsumirAPI consumirAPI = new ConsumirAPI();
    private  final ConverterDados conversorDados = new ConverterDados();
    public void exibirMenu(){

        System.out.println("""
                Bem Vindo a Consulta a Tabela FIPE!
                
                ***********ESCOLHA UMA OPÇAO************
                Moto
                Carro
                Caminhao
                
                Digite a opção escolhida:
                
                """);
        String opcao = leitura.nextLine().toLowerCase();


        String endereco = null;

        switch (opcao){
            case "moto":
                endereco = URL_BASE + "/motos/marcas";
                break;
            case "carro":
                endereco = URL_BASE + "/carros/marcas";
                break;
            case "caminhao":
                endereco = URL_BASE + "/caminhoes/marcas";
                break;


        }


        var json = consumirAPI.obterDados(endereco);


        var marcas = conversorDados.obterLista(json, Dados.class);
        marcas.forEach(m -> System.out.println("Codigo: " + m.codigo() + " Marca: " + m.nome()));


        System.out.println("Digite o código da marca desejada: ");
        opcao = leitura.nextLine();

        endereco = endereco + "/" + opcao  + "/modelos";
        json = consumirAPI.obterDados(endereco);

        var modelos = conversorDados.obterDados(json, DadosModelos.class);


        modelos.modelos().forEach(d -> System.out.println("Código: " + d.codigo() + " Modelo: " + d.nome()));

        System.out.println("Escreva um parte do nome do modelo: ");
        String modelo  = leitura.nextLine();

        String finalModelo = modelo;
        modelos.modelos().stream()
                .filter(d -> d.nome().toLowerCase().contains(finalModelo.toLowerCase()))
                .collect(Collectors.toList())
                .forEach(d-> System.out.println("Código: " + d.codigo() + " Modelo: " + d.nome()));

        System.out.println("Digite o código do Modelo desejado: ");
        modelo = leitura.nextLine();

        endereco = endereco + "/" + modelo + "/anos";
        json = consumirAPI.obterDados(endereco);
        var anos = conversorDados.obterLista(json, Dados.class);


        List<DadosCarro> carros = new ArrayList<>();
        for(var dados : anos) {
            json = consumirAPI.obterDados(endereco + "/"+dados.codigo());
            var carro = conversorDados.obterDados(json, DadosCarro.class);
            carros.add(carro);
        }

        carros.forEach(c-> System.out.println(" Marca:  " + c.marca() + " Modelo: " + c.modelo() +" Ano: " + c.anoModelo() +  " Valor: " + c.valor()));









    }
}
