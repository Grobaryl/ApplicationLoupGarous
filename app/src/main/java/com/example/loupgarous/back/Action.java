package com.example.loupgarous.back;

public class Action {

    private Actable target;
    private ActionType type;

    public Action(Actable a, ActionType t){
        this.target  = a;
        this.type = t;
    }

    @Override
    public String toString(){
        return target.hashCode() + "," + type.name();
    }


    public ActionType getType() {
        return this.type;
    }

    public Actable getTarget() {
        return this.target;
    }
}
