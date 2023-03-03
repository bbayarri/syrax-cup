package com.syrax.domain.Players;

import com.syrax.domain.Element;

import java.util.Random;

public class ANPlayer implements Player {

    private static int PIEDRA_CONT = 0;
    private static int PAPEL_CONT = 0;
    private static int TIJERA_CONT = 0;
    private static boolean PARTIDA_GANADA = false;

    @Override
    public String getName() {
        return "ANPlayer";
    }

    @Override
    public Element play() {
        Random random = new Random();
        Element jugada = Element.PAPEL;

        switch (random.nextInt(3)) {
            case 0:
                jugada = Element.PAPEL;
                break;
            case 1:
                jugada = Element.PIEDRA;
                break;
            case 2:
                jugada = Element.TIJERA;
        }

        if(PIEDRA_CONT < PAPEL_CONT && PIEDRA_CONT < TIJERA_CONT)
            jugada = Element.PIEDRA;

        if(PAPEL_CONT < PIEDRA_CONT && PAPEL_CONT < TIJERA_CONT)
            jugada = Element.PAPEL;

        if(TIJERA_CONT < PAPEL_CONT && TIJERA_CONT < PIEDRA_CONT)
            jugada = Element.PAPEL;

        if(!PARTIDA_GANADA) {
            if(jugada == Element.PIEDRA)
                jugada = Element.PAPEL;

            if(jugada == Element.PAPEL)
                jugada = Element.TIJERA;

            if(jugada == Element.TIJERA)
                jugada = Element.PIEDRA;
        }

        return jugada;
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

        if(myElement == Element.PAPEL && rivalElement == Element.PIEDRA)
            PARTIDA_GANADA = true;

        if(myElement == Element.PIEDRA && rivalElement == Element.PAPEL)
            PARTIDA_GANADA = false;

        if(myElement == Element.PIEDRA && rivalElement == Element.TIJERA)
            PARTIDA_GANADA = true;

        if(myElement == Element.TIJERA && rivalElement == Element.PIEDRA)
            PARTIDA_GANADA = false;

        if(myElement == Element.TIJERA && rivalElement == Element.PAPEL)
            PARTIDA_GANADA = true;

        if(myElement == Element.PAPEL && rivalElement == Element.TIJERA)
            PARTIDA_GANADA = false;
    }

    @Override
    public void clean() {
        PIEDRA_CONT = 0;
        PAPEL_CONT = 0;
        TIJERA_CONT = 0;
        PARTIDA_GANADA = false;
    }

}
