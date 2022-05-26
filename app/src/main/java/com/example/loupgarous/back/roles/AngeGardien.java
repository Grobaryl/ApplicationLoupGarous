package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Action;
import com.example.loupgarous.back.ActionType;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class AngeGardien extends Role {

    public AngeGardien(){
        super();
        this.team = Team.VILLAGE;
        this.roleNameId = R.string.role_anga_name;
        this.roleDescriptionId = R.string.role_anga_description;
        this.rolePicId = R.drawable.ange_gardien;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

        Action a;
        a = new Action(targets.get(0),ActionType.PROTECTED);
        user.addAction(a);
    }
}
