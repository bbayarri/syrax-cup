package com.syrax.domain.Players;

import com.syrax.domain.Element;

public interface Player {
    //Retorna el nombre del jugador
    String getName();

    //Retorna el elemento elegido para hacer la jugada
    Element play();

    //Se recibe el elemento jugado y el elemento que jugo el rival
    void learn(Element myElement, Element rivalElement);

    //Hacemos limpieza de todas las variables usadas (en caso de ser necesario)
    void clean();
}
