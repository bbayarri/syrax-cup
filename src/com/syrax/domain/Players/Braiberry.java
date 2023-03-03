package com.syrax.domain.Players;

import com.syrax.domain.Element;

import java.util.Random;

public class Braiberry implements Player {

    private static int ROUND_COUNT = 0;
    private static int PIEDRA_CONT = 0;
    private static int PAPEL_CONT = 0;
    private static int TIJERA_CONT = 0;
    private static int DEFENSIVE_MODE_CONT = 0;
    private static int DEFENSIVE_MODE_LIMIT = 0;

    private boolean phaseRandom;
    private boolean phasePiedraAttacking;
    private boolean phaseTijeraAttacking;
    private boolean phasePiedraDefensive;
    private boolean phasePapelDefensive;
    private boolean phaseTijeraDefensive;
    private boolean defensiveMode;

    public Braiberry() {
        phaseRandom = true;
        phasePiedraAttacking = false;
        phaseTijeraAttacking = false;
        phasePiedraDefensive = false;
        phasePapelDefensive = false;
        phaseTijeraDefensive = false;
        defensiveMode = false;
    }

    @Override
    public String getName() {
        return "Braiberry";
    }

    @Override
    public Element play() {
        ROUND_COUNT++;

        if(phasePiedraDefensive) {
            return playPhasePapel();
        }

        if(phasePapelDefensive) {
            return playPhaseTijera();
        }

        if(phaseTijeraDefensive) {
            return playPhasePiedra();
        }

        if(phaseRandom) {
            return playPhaseRandom();
        }

        if(phasePiedraAttacking) {
            return playPhasePiedra();
        }

        if(phaseTijeraAttacking) {
            return playPhaseTijera();
        }

        return Element.TIJERA;

    }

    private Element playPhaseRandom() {
        Random rand = new Random();

        int upperbound = 90;
        int number = rand.nextInt(upperbound);

        if(number < 30) {
            return Element.PIEDRA;
        } else {
            if(number < 60) {
                return Element.TIJERA;
            } else {
                return Element.PAPEL;
            }
        }
    }

    private Element playPhasePiedra() {
        return Element.PIEDRA;
    }

    private Element playPhasePapel() {
        return Element.PAPEL;
    }

    private Element playPhaseTijera() {
        return Element.TIJERA;
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

        evaluateDefensivePhase();
        evaluateEndDefensiveMode();
        evaluateChangeOfPhase();
    }

    private void evaluateEndDefensiveMode() {
        if(defensiveMode) {
            if(DEFENSIVE_MODE_CONT == DEFENSIVE_MODE_LIMIT) {
                defensiveMode = false;
                DEFENSIVE_MODE_CONT = 0;
                DEFENSIVE_MODE_LIMIT = 0;
            }
        }
    }

    private void evaluateChangeOfPhase() {
        if(!defensiveMode) {
            if(ROUND_COUNT > 501 && ROUND_COUNT < 3000) {
                changeOfPhase(false, true, false, false, false, false);
            } else if(ROUND_COUNT >= 3001 && ROUND_COUNT < 3657) {
                changeOfPhase(true, false, false, false, false, false);
            } else if(ROUND_COUNT >= 3657 && ROUND_COUNT <= 8800) {
                changeOfPhase(false, false, true, false, false, false);
            }
        }
    }

    private void evaluateDefensivePhase() {
        if(ROUND_COUNT >= 4500) {
            int piedraPercentage = PIEDRA_CONT * 100 / ROUND_COUNT;
            int papelPercentage = PAPEL_CONT * 100 / ROUND_COUNT;
            int tijeraPercentage = TIJERA_CONT * 100 / ROUND_COUNT;

            if(piedraPercentage > 63) {
                changeOfPhase(false, false, false, true, false, false);
                defensiveMode = true;
                calculateDefensiveModeLimit();
            } else if(papelPercentage > 60) {
                changeOfPhase(false, false, false,false, true, false);
                defensiveMode = true;
                calculateDefensiveModeLimit();
            } else if(tijeraPercentage > 45) {
                changeOfPhase(false, false, false,false, false, true);
                defensiveMode = true;
                calculateDefensiveModeLimit();
            }
        }
    }

    private void calculateDefensiveModeLimit() {
        int roundsToPlay = 10000 - ROUND_COUNT;
        DEFENSIVE_MODE_LIMIT = 38 * roundsToPlay / 100;
    }

    private void changeOfPhase(boolean random, boolean piedraAttacking, boolean tijeraAttacking, boolean piedraDefensive, boolean papelDefensive, boolean tijeraDefensive) {
        phaseRandom = random;
        phasePiedraAttacking = piedraAttacking;
        phaseTijeraAttacking = tijeraAttacking;
        phasePiedraDefensive = piedraDefensive;
        phasePapelDefensive = papelDefensive;
        phaseTijeraDefensive = tijeraDefensive;
    }

    @Override
    public void clean() {
        phaseRandom = true;
        phasePiedraAttacking = false;
        phaseTijeraAttacking = false;
        phasePiedraDefensive = false;
        phasePapelDefensive = false;
        phaseTijeraDefensive = false;
        ROUND_COUNT = 0;
        PIEDRA_CONT = 0;
        PAPEL_CONT = 0;
        TIJERA_CONT = 0;
        DEFENSIVE_MODE_CONT = 0;
        DEFENSIVE_MODE_LIMIT = 0;
    }

}
