package com.syrax.domain.Players;

import com.syrax.domain.Element;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.syrax.domain.Element.PAPEL;
import static com.syrax.domain.Element.TIJERA;
import static com.syrax.domain.Element.PIEDRA;

public class BestPlayer implements Player {

    private int PIEDRA_CONT = 0;
    private int PAPEL_CONT = 0;
    private int TIJERA_CONT = 0;
    private Element ourEnemysLastCall;

    @Override
    public String getName() {
        return "Michael Jordan";
    }

    @Override
    public Element play() {
        if (ourEnemysLastCall == null) {
            return PAPEL;
        }

        return seekAndDestroy(ourEnemysLastCall);
    }

    private Element seekAndDestroy(Element ourEnemysLastCall) {
        Element elementToDiscard = getContrary(ourEnemysLastCall);
        List<Element> allElements = Arrays.asList(PAPEL, PIEDRA, TIJERA);

        // TODO: Si el enemigo tiene un % superior de una opción sobre las otras ir por su contraria
//        if (enemyHasPreferredOption()) {
//            return getContrary(getMostUsedElement());
//        }

        List<Element> options = allElements.stream()
                .filter(element -> !element.equals(elementToDiscard))
                .collect(Collectors.toList());

        return getRandomBetweenOptions(options);
    }

    private boolean enemyHasPreferredOption() {
        int total = PIEDRA_CONT + TIJERA_CONT + PAPEL_CONT;

        // CHAT GPT

        return false;
    }

    private Element getMostUsedElement() {
        return null;
    }

    private Element getRandomBetweenOptions(List<Element> allElements) {
        Random rand = new Random();
        return allElements.get(rand.nextInt(allElements.size()));
    }

    private Element getContrary(Element ourEnemysLastCall) {
        HashMap<Element, Element> contraries = new HashMap<>();
        contraries.put(PAPEL, TIJERA);
        contraries.put(TIJERA, PIEDRA);
        contraries.put(PIEDRA, PAPEL);
        return contraries.get(ourEnemysLastCall);
    }

    @Override
    public void learn(Element myElement, Element rivalElement) {
        ourEnemysLastCall = rivalElement;

        if (rivalElement.equals(Element.PIEDRA)) {
            PIEDRA_CONT++;
        } else if (rivalElement.equals(PAPEL)) {
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

