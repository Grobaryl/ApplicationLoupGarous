package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Action;
import com.example.loupgarous.back.ActionType;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class AngeDechu extends Role {

    public AngeDechu(){
        super();
        this.team = Team.ANGE;
        this.roleNameId = R.string.role_anmo_name;
        this.roleDescriptionId = R.string.role_anmo_description;
        this.rolePicId = R.drawable.ange_dechu;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

        Action a;
        a = new Action(targets.get(0),ActionType.TARGETED);
        user.addAction(a);
    }
}
