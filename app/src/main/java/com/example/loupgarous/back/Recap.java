package com.example.loupgarous.back;

import java.util.ArrayList;
import java.util.List;

/**
 * Cet objet n'est utilisé que pour transmettre les données entre la fonction verifiant les gagnants et le frontend.
 */
public class Recap {

    public Recap(){
        winners = new ArrayList<>();
        loosers = new ArrayList<>();
    }
    public List<Player> winners;
    public List<Player> loosers;
    public Player votedOff;
    public Team winningTeam;

}
