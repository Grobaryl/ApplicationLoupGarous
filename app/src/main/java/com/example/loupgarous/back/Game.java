package com.example.loupgarous.back;

import com.example.loupgarous.back.roles.AngeGardien;
import com.example.loupgarous.back.roles.AngeDechu;
import com.example.loupgarous.back.roles.Bouc;
import com.example.loupgarous.back.roles.Chouette;
import com.example.loupgarous.back.roles.Mort;
import com.example.loupgarous.back.roles.Hibou;
import com.example.loupgarous.back.roles.Joker;
import com.example.loupgarous.back.roles.Metamorphe;
import com.example.loupgarous.back.roles.Voyante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {


    private static Game instance;
    private List<Role> remainingPool;
    private List<Player> players;

    public static Game getInstance(){
        if(instance == null){
            Game g =  new Game();
            instance = g;
        }
        return instance;
    }

    public void initGame(String[] playerNames){
        List<Role> pool = new ArrayList<Role>();

        players = new ArrayList<>();
        Role meta = new Metamorphe();
        pool.add(new Voyante());
        pool.add(new Chouette());
        pool.add(new Hibou());
        Bouc bouc = new Bouc();
        pool.add(bouc);
        pool.add(new AngeGardien());
        pool.add(new AngeDechu());
        pool.add(new Joker());
        pool.add(new Mort());
        pool.add(meta);

        Collections.shuffle(pool);

        for(int i=0;i<5;i++){
            players.add(new Player(pool.remove(0),playerNames[i]));
        }
        if (pool.contains(meta))
            bouc.switchTeam();
        remainingPool = pool;
    }


    public Recap getWinner(){

        Recap recap = new Recap();

        //Echange des roles pour le résultat de la partie
        for(Player p : players){
            List<Action> l = p.getActions(ActionType.SWAPPED);
            if(l!=null){
                Player p0 = ((Player)l.get(0).getTarget());
                Player p1 = ((Player)l.get(1).getTarget());
                Role swap = p0.getRole();
                p0.setRole(p1.getRole());
                p1.setRole(swap);
            }
        }

        //résoltion et compte des votes
        Map<Player,Integer> voteResults = new HashMap<>();
        for(Player p : players){
            voteResults.put(p,0);
        }
        for(Player p : players){
            List<Action> l = p.getActions(ActionType.VOTED);
            if(l!= null)
                voteResults.put((Player)l.get(0).getTarget(),voteResults.get(l.get(0).getTarget())+1);
        }
        Player votedOff = null;
        int max = 0;
        int sec =0; // une égalité veut dire que personne en meurt, on doit donc le prendre en compte
        for(Player p : players){
            if(voteResults.get(p)>max) {
                votedOff = p;
                sec = max;
                max = voteResults.get(p);
            }
        }
        if(sec!=max) {
            votedOff.kill();
            recap.votedOff = votedOff;

            //Si la cible de l'ange est votée, il gagne
            for (Player p : players) {
                List<Action> l = p.getActions(ActionType.TARGETED);
                if (l != null) {
                    if (l.get(0).getTarget().equals(votedOff)) {
                        fillRecap(recap,Team.ANGE);
                        return recap;
                    };
                }
            }
            //Sinon, si le bouc est voté, son camp gagne
            if (votedOff.getRole().getClass() == Bouc.class) {
                if (containsRole(Metamorphe.class)) {
                    fillRecap(recap,Team.LOUPGAROU);
                    return recap;
                } else{
                    fillRecap(recap,Team.ANGE);
                    return recap;
                }
            }
        }
        //Si le métamorphe et présent et a été tué, le village gagne, sinon, il gagne
        for (Player p:players ) {
            if (p.getRole().getClass()==Metamorphe.class){
                if(p.isDead()){
                    fillRecap(recap,Team.VILLAGE);
                    return recap;
                }
                else{
                    fillRecap(recap,Team.LOUPGAROU);
                    return recap;
                }
            }
        }

        //sinon, le village gagne.

        fillRecap(recap,Team.VILLAGE);
        return recap;
    }

    /**
     *  returns player with given role, or null if no player has this role
     * @param r
     * @return
     */
    private Player getPlayerWithRole(Class<? extends Role> r){
        for (Player p :players) {
            if(p.isRole(r)) return p;
        }
        return null;
    }

    public void fillRecap(Recap r,Team winner){
        for (Player p :players) {
            if (p.getRole().getTeam()==winner)
                r.winners.add(p);
            else
                r.loosers.add(p);
        }
        r.winningTeam = winner;
    }

    /**
     *
     * @param voter
     * @param voted
     * @return false if the vote couldnt be processed.
     */
    public boolean voteFor(int voter, int voted){
        if(voter == voted||players.get(voted).isDead())
            return false;
        this.players.get(voter).addAction(new Action(this.players.get(voted),ActionType.VOTED));
        return true;
    }

    public Player getPlayer(int i){
        return this.players.get(i);
    }

    public boolean containsRole(Class<? extends  Role> c){
        return (getPlayerWithRole(c)!=null);
    }


    public Role playTransform(int choice){
        Role r = remainingPool.get(choice);
        Player p = getPlayerWithRole(Metamorphe.class);
        if(p!=null){
            ((Metamorphe)p.getRole()).power = r;
            List<Actable> targets = new ArrayList<>();
            targets.add(r);
            p.getRole().usePower(p, targets);
        }
        return r;
    }

    public Role playChouette(int choice){
        Role r = remainingPool.get(choice);
        Player p = getPlayerWithRole(Chouette.class);
        if(p!=null){
            List<Actable> targets = new ArrayList<>();
            targets.add(r);
            if(p.getRole().getClass()==Chouette.class)
                p.getRole().usePower(p, targets);
            else {
                ((Metamorphe) p.getRole()).power.usePower(p, targets);
            }
        }
        return r;
    }

    public void playJoker(String playername1,String playername2){
        Player p1 = null,p2 = null;
        for (Player p : players) {
            if (p.getName().equals(playername1))
                p1 = p;
            if (p.getName().equals(playername2))
                p2 = p;
        }
        Player p = getPlayerWithRole(Joker.class);
        if(p!=null){
                List<Actable> targets = new ArrayList<>();
                targets.add(p1);
                targets.add(p2);
                if(p.getRole().getClass()==Joker.class)
                    p.getRole().usePower(p, targets);
                else{
                    ((Metamorphe)p.getRole()).power.usePower(p,targets);
                }

        }
    }

    public void playAngeG(String playername){
        Player p1 = null;
        for (Player p : players) {
            if (p.getName().equals(playername))
                p1 = p;
        }
        Player p = getPlayerWithRole(AngeGardien.class);
        if(p!=null){
                List<Actable> targets = new ArrayList<>();
                targets.add(p1);
                if(p.getRole().getClass()==AngeGardien.class)
                    p.getRole().usePower(p, targets);
                else{
                    ((Metamorphe)p.getRole()).power.usePower(p,targets);
                }
        }
    }

    public void playMetamorphe(String playername){
        Player p1 = null;
        for (Player p : players) {
            if (p.getName().equals(playername))
                p1 = p;
        }
        Player p = getPlayerWithRole(Metamorphe.class);
        if(p!=null){
            List<Actable> targets = new ArrayList<>();
            targets.add(p1);
            if(!isProtected(p1)) {
                p.getRole().usePower(p, targets);
                p1.kill();
            }
        }
    }

    public void playAngeD(String playername){
        Player p1 = null;
        for (Player p : players) {
            if (p.getName().equals(playername))
                p1 = p;
        }
        Player p = getPlayerWithRole(AngeDechu.class);
        if(p!=null){
            List<Actable> targets = new ArrayList<>();
            targets.add(p1.getRole());
            if(p.getRole().getClass()==AngeDechu.class)
                p.getRole().usePower(p, targets);
            else{
                ((Metamorphe)p.getRole()).power.usePower(p,targets);
            }
        }
    }

    /**
     * this option wasn't added in the current version of the game.
     * @param playername
     * @return
     */
    public Role playVoyante(String playername){
        Player p1 = null;
        for (Player p : players) {
            if (p.getName().equals(playername))
                p1 = p;
        }
        for (Player p : players) {
            if (p.isRole(Voyante.class)) {
                List<Actable> targets = new ArrayList<>();
                targets.add(p1.getRole());
                p.getRole().usePower(p, targets);
                return p1.getRole();
            }
        }
        return null;
    }

    public List<Role> playVoyante(int choice1, int choice2){
        List<Role> l = new ArrayList<>();
        Role r1 = remainingPool.get(choice1);
        Role r2 = remainingPool.get(choice2);
        l.add(r1);
        l.add(r2);
        Player p = getPlayerWithRole(Voyante.class);
        if(p!=null){
            List<Actable> targets = new ArrayList<>();
            targets.add(r1);
            targets.add(r2);
            if(p.getRole().getClass()==Voyante.class)
                p.getRole().usePower(p, targets);
            else{
                ((Metamorphe)p.getRole()).power.usePower(p,targets);
            }
            return l;
        }
        return null;
    }


    public void playMort(String playername) {
        Player p1 = null;
        for (Player p : players) {
            if (p.getName().equals(playername))
                p1 = p;
        }
        Player p = getPlayerWithRole(Mort.class);
        if(p!=null){
            List<Actable> targets = new ArrayList<>();
            targets.add(p1);
            if(p.getRole().getClass()==Voyante.class)
                p.getRole().usePower(p, targets);
            else{
                ((Metamorphe)p.getRole()).power.usePower(p,targets);
            }
        }
    }


    public Player getEaten() {
        for(Player p : players){
            List<Action> l = p.getActions(ActionType.KILLED);
            if (l != null)
                return (Player)l.get(0).getTarget();
        }
        return null;
    }

    public boolean isProtected(Player p0){
        for(Player p : players){
            List<Action> l = p.getActions(ActionType.PROTECTED);
            if (l!=null){
                System.out.println("found protected, comparing"+l.get(0).getTarget()+" with "+p0);
                if(l.get(0).getTarget().equals(p0))
                    return true;
            }
        }
        return false;
    }

    public boolean isMortMorte() {
        for(Player p : players){
            if (p.isRole(Mort.class)&&p.isDead()){
                return true;
            }
        }
        return false;
    }

}
