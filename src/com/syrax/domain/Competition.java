package com.syrax.domain;

import com.syrax.domain.Players.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Competition {

    private final Player[] players;
    private final Referee referee;
    private final Scanner sc;
    private final Map<Player, Long> points;

    public Competition(Player[] players, Referee referee) {
        this.players = players;
        this.referee = referee;
        this.sc = new Scanner(System.in);
        this.points = new HashMap<>();

        for (Player player : players) {
            this.points.put(player, 0L);
        }

    }

    public Player start() {
        for (int i = 0; i < players.length; i += 2) {
            match(players[i], players[(i + 1) % players.length]);
        }

        for (int i = 1; i < players.length; i += 2) {
            match(players[i], players[(i + 1) % players.length]);
        }


        for (int dist = 2; dist < (players.length + 1) / 2; dist++) {
            for (int i = 0; i < players.length; i++) {
                match(players[i], players[(i + dist) % players.length]);
            }
        }

        if (players.length % 2 == 0) {
            for (int i = 0; i < players.length / 2; i++) {
                match(players[i], players[i + players.length / 2]);
            }
        }

        return getWinner();

    }

    private void match(Player homePlayer, Player visitingPlayer) {
        System.out.println("\u001B[32m" + homePlayer.getName() + " vs " + visitingPlayer.getName() + "\u001B[0m");

        sc.nextLine();

        Long homePlayerPoints = 0L;
        Long visitingPlayerPoints = 0L;
        Long tiePoints = 0L;

        for (int i = 0; i < 10000; i++) {
            Element homeElement = homePlayer.play();
            Element visitingElement = visitingPlayer.play();

            homePlayer.learn(homeElement, visitingElement);
            visitingPlayer.learn(visitingElement, homeElement);

            Result result = referee.validate(homeElement, visitingElement);

            if (result.equals(Result.WIN)) {
                homePlayerPoints++;
            } else if (result.equals(Result.LOSE)) {
                visitingPlayerPoints++;
            } else {
                tiePoints++;
            }

        }

        points.merge(homePlayer, homePlayerPoints, Long::sum);
        points.merge(visitingPlayer, visitingPlayerPoints, Long::sum);

        homePlayer.clean();
        visitingPlayer.clean();

        System.out.println("Resultado final:");
        sc.nextLine();
        System.out.printf("* Empataron %d veces\n", tiePoints);
        sc.nextLine();
        System.out.printf("* %s gano %d veces\n", homePlayer.getName(), homePlayerPoints);
        sc.nextLine();
        System.out.printf("* %s gano %d veces\n", visitingPlayer.getName(), visitingPlayerPoints);

        sc.nextLine();

        printTable();
    }

    private void printTable() {
        System.out.println("\u001B[33m" + "TABLA DE POSICIONES" + "\u001B[0m");
        System.out.println("\u001B[33m" + "...................." + "\u001B[0m");

        Map<Player, Long> sortedMap = points.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        for (Map.Entry<Player, Long> entry : sortedMap.entrySet()) {
            System.out.println("\u001B[33m" + entry.getKey().getName() + " : " + entry.getValue().toString() + " pts" + "\u001B[0m");
        }

        sc.nextLine();
    }

    private Player getWinner() {
        Map.Entry<Player, Long> maxEntry = Collections.max(points.entrySet(), Map.Entry.comparingByValue());
        return maxEntry.getKey();
    }

}
