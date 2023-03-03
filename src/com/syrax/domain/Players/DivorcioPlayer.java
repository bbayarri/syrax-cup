package com.syrax.domain.Players;

import static com.syrax.domain.Element.PAPEL;
import static com.syrax.domain.Element.PIEDRA;
import static com.syrax.domain.Element.TIJERA;

import com.syrax.domain.Element;

import java.util.ArrayList;
import java.util.List;

public class DivorcioPlayer implements Player {
    List<Element> opponentMoves = new ArrayList<>();

    @Override
    public String getName() {
        return "El divorciado";
    }

    @Override
    public Element play() {
        if (opponentMoves.size()>=1000) partialClean();

        int size = opponentMoves.size();

        for (int i = size; i > 0; i--) {
            List<Element> lastSequence = opponentMoves.subList(size - i, size);
            for (int j = size - (i + 1); j >= 0; j--) {
                List<Element> candidateSequence = opponentMoves.subList(j, j + i);
                if (candidateSequence.equals(lastSequence)) {
                    return getBeater(opponentMoves.get(j + i));
                }
            }
        }
        return PIEDRA;
    }

    private void partialClean() {
        int currentSize = opponentMoves.size();
        opponentMoves=opponentMoves.subList(currentSize -50, currentSize);
    }

    @Override
    public void learn(Element myElement, Element rivalElement) {
        opponentMoves.add(rivalElement);
    }

    @Override
    public void clean() {
        opponentMoves = new ArrayList<>();

    }


    private Element getBeater(Element elemnet) {
        if (TIJERA.equals(elemnet)) return PIEDRA;
        if (PIEDRA.equals(elemnet)) return PAPEL;
        if (PAPEL.equals(elemnet)) return TIJERA;
        return PIEDRA;
    }
}
