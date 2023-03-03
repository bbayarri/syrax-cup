package com.syrax.domain.Players;

import com.syrax.domain.Element;

import java.util.Random;

public class Player1 implements Player {

    private static int PIEDRA_CONT = 0;
    private static int PAPEL_CONT = 0;
    private static int TIJERA_CONT = 0;

    @Override
    public String getName() {
        return "Messi";
    }

    @Override
    public Element play() {
        Random rand = new Random();

        int upperbound = 90;
        int number = rand.nextInt(upperbound);

        if (number < 30) {
            return Element.PIEDRA;
        } else {
            if (number < 60) {
                return Element.TIJERA;
            } else if (number < 80) {
                return Element.PAPEL;
            } else {
                if (PIEDRA_CONT > 50) {
                    return Element.PAPEL;
                } else {
                    if (TIJERA_CONT % 2 == 0) {
                        return Element.TIJERA;
                    } else {
                        return Element.PIEDRA;
                    }

                }
            }
        }
    }

    @Override
    public void learn(Element myElement, Element rivalElement) {
        if (rivalElement.equals(Element.PIEDRA)) {
            PIEDRA_CONT++;
        } else if (rivalElement.equals(Element.PAPEL)) {
            PAPEL_CONT++;
        } else {
            TIJERA_CONT++;
        }
    }

    @Override
    public void clean() {
        PIEDRA_CONT = 0;
        PAPEL_CONT = 0;
        TIJERA_CONT = 0;
    }

}
