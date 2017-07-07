package main.java.co.pruebas.futuros;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by abelmeos on 2017/07/07.
 */
public class ValidadorPoliticas {


    public double getValidarPolitica(String empleado){

        return validarPolitica(empleado);

    }

    public Future<Double> getValidacionAsincrona(String empleado){

        CompletableFuture<Double>  politicaFuturo = new CompletableFuture<>();


        new Thread(() -> {

            double politica = validarPolitica(empleado);
            politicaFuturo.complete(politica);

        }).start();

        return politicaFuturo;

    }

    private double validarPolitica(String empleado){

        Random random = new Random();
        delay();
        return random.nextDouble() * empleado.charAt(0) +  empleado.charAt(1);

    }

    public static void delay(){

        try {
            Thread.sleep(10000L);
        }catch (InterruptedException e){
            throw  new RuntimeException(e);

        }

    }


}
