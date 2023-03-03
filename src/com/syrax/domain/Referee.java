package com.syrax.domain;

public class Referee {

    public Result validate(Element homeElement, Element visitingElement) {
        switch (homeElement) {
            case PIEDRA:
                switch (visitingElement) {
                    case PIEDRA:
                        return Result.TIE;
                    case PAPEL:
                        return Result.LOSE;
                    case TIJERA:
                        return Result.WIN;
                }
                break;

            case PAPEL:
                switch (visitingElement) {
                    case PIEDRA:
                        return Result.WIN;
                    case PAPEL:
                        return Result.TIE;
                    case TIJERA:
                        return Result.LOSE;
                }
                break;

            case TIJERA:
                switch (visitingElement) {
                    case PIEDRA:
                        return Result.LOSE;
                    case PAPEL:
                        return Result.WIN;
                    case TIJERA:
                        return Result.TIE;
                }
                break;
        }

        return Result.INVALID;
    }
}
