package com.syrax.domain.Players;

import com.syrax.domain.Element;

public class Player2 implements Player {

    @Override
    public String getName() {
        return "Mbappe";
    }

    @Override
    public Element play() {
        return Element.PAPEL;
    }

    @Override
    public void learn(Element myElement, Element rivalElement) {

    }

    @Override
    public void clean() {

    }
}
