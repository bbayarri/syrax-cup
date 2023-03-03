package com.syrax;

import com.syrax.domain.Competition;
import com.syrax.domain.Players.*;
import com.syrax.domain.Referee;

public class Main {

    /*
    COMPETENCIA:
    - UNA PARTIDA CONSTA DE 10.000 RONDAS
    - EN CADA RONDA SE LLAMA EL METODO PLAY DE CADA JUGADOR PARA QUE JUEGUE PIEDRA, PAPEL O TIJERA
    - AL FINALIZAR CADA RONDA SE LLAMARA EL METODO LEARN PARA QUE CADA JUGADOR APRENDA SOBRE LA RONDA JUGADA
    - AL FINALIZAR UNA PARTIDA SE LLAMARA AL METODO CLEAN PARA REALIZAR LIMPIEZA DE LOS RECURSOS USADOS
    - CADA RONDA GANADA POR UN JUGADOR SUMARA 1 PUNTO
    - EL JUGADOR QUE MAS PUNTOS SUME SERA EL GANADOR
 */

    public static void main(String[] args) {
        Player[] players = {new BestPlayer(), new Braiberry(), new ANPlayer(), new SkyNet(), new DivorcioPlayer()};
        Referee referee = new Referee();

        System.out.println("\u001B[34m" + "----------------------------" + "\u001B[0m");
        System.out.println("\u001B[34m" + "BIENVENIDOS A LA COPA SYRAX" + "\u001B[0m");
        System.out.println("\u001B[34m" + "----------------------------" + "\u001B[0m" + "\n");

        Competition competition = new Competition(players, referee);

        Player winner = competition.start();

        System.out.printf("El ganador es %s%n", winner.getName());
    }
}



