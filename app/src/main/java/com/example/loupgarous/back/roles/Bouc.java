package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Action;
import com.example.loupgarous.back.ActionType;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class Bouc extends Role {

    public Bouc(){
        super();
        this.team = Team.LOUPGAROU;
        this.roleNameId = R.string.role_bouc_name;
        this.roleDescriptionId = R.string.role_bouc_description;
        this.rolePicId = R.drawable.bouc;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

        Action a;
        a = new Action(targets.get(0),ActionType.TARGETED);
        user.addAction(a);
    }

    public void switchTeam(){
        this.team = Team.ANGE;
    }
}
