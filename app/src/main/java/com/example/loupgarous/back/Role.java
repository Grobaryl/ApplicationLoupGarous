package com.example.loupgarous.back;

import java.util.List;

public abstract class Role implements Actable{
    protected Team team;
    protected int roleNameId;
    protected int roleDescriptionId;
    protected int rolePicId;
    public Role(){

    }

    public abstract void usePower(Player user, List<Actable> targets);

    public int getRoleName(){
        return this.roleNameId;
    }

    public int getRoleDesc(){
        return this.roleDescriptionId;
    }

    public int getRolePic(){
        return this.rolePicId;
    }

    public Team getTeam() {
        return this.team;
    }
}
