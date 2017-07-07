package main.java.co.pruebas.futuros;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by abelmeos on 2017/07/07.
 */
public class MainValidarPoliticas {

    public static void main(String args[]){


        List<String> nombres = Arrays.asList("Alejandro", "juan", "Pepe");

        ValidadorPoliticas validadorPoliticas = new ValidadorPoliticas();


        long start = System.nanoTime();

        List<Double> consulta = nombres.stream()
                 .map(nombre -> validadorPoliticas.getValidarPolitica(nombre)).collect(Collectors.toList());

        long finalTime = (System.nanoTime() - start)/ 1_000_000;

        System.out.println(consulta);
        System.out.println(finalTime);


        long start2 = System.nanoTime();

        List<CompletableFuture<Double>> politicasFuture = nombres.stream().
                map(nombre -> CompletableFuture.supplyAsync(
                        ()-> validadorPoliticas.getValidarPolitica(nombre)
                )).collect(Collectors.toList());

        List<Double> resultados = politicasFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());


        long finalTime2 = (System.nanoTime() - start2)/ 1_000_000;


        System.out.println(resultados);
        System.out.println(finalTime2);
    }

}
