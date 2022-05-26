package com.example.loupgarous.back;


import com.example.loupgarous.back.roles.Joker;
import com.example.loupgarous.back.roles.Metamorphe;

import java.util.ArrayList;
import java.util.List;

public class Player implements Actable{

    private Role role;
    private String name;
    private List<Action> actions;
    private boolean dead;

    public Player(Role r,String n0){
        this.role = r;
        this.name = n0;
        this.actions = new ArrayList<Action>();
        this.dead = false;
    }


    public void addAction(Action a){
        actions.add(a);
        System.out.println("AJOUTEEEED");
    }

    public List<Action> getActions(ActionType t){
        List<Action> l = new ArrayList<>();
        for(Action a : actions){
            if(a.getType() == t)
                l.add(a);
        }
        if(l.isEmpty())
            return null;
        return l;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role r) {
        this.role = r;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(name+" "+role.toString()+": ");
        for(Action a : actions) {
            s.append("("+a+")");
        }
        return s.toString();
    }

    public boolean isRole(Class<? extends Role> roleClass) {
        return role.getClass() == roleClass||((role.getClass() == Metamorphe.class)&&((Metamorphe)role).power.getClass() == roleClass);
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        this.dead = true;
    }
}
