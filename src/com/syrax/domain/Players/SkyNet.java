package com.syrax.domain.Players;

import com.syrax.domain.Element;

import java.util.Random;

public class SkyNet implements Player{

    private static int PIEDRA_CONT = 0;
    private static int PAPEL_CONT = 0;
    private static int TIJERA_CONT = 0;

    private static double PIEDRA_PROB = 0.33;
    private static double PAPEL_PROB = 0.33;
    private static double TIJERA_PROB = 0.33;

    private static int PARTIDAS = 0;

    @Override
    public String getName() {
        return "Skynet-V2";
    }

    @Override
    public Element play() {
        Random rand = new Random();

        double number = rand.nextDouble();

        if (number < PIEDRA_PROB) {
            return Element.PAPEL;
        } else if (number < PAPEL_PROB * 2) {
            return Element.TIJERA;
        }
        return Element.PIEDRA;
    }

    @Override
    public void learn(Element myElement, Element rivalElement) {
        PARTIDAS++;
        if (rivalElement.equals(Element.PIEDRA)) {
            PIEDRA_CONT++;
        } else if (rivalElement.equals(Element.PAPEL)) {
            PAPEL_CONT++;
        } else {
            TIJERA_CONT++;
        }
        adjustWeigth();
    }

    private void adjustWeigth() {
        PIEDRA_PROB = PIEDRA_CONT / PARTIDAS;
        PAPEL_PROB = PAPEL_CONT / PARTIDAS;
        TIJERA_PROB = TIJERA_CONT / PARTIDAS;
    }

    @Override
    public void clean() {
        PIEDRA_CONT = 0;
        PAPEL_CONT = 0;
        TIJERA_CONT = 0;

        PIEDRA_PROB = 0.33;
        PAPEL_PROB = 0.33;
        TIJERA_PROB = 0.33;
    }
}
