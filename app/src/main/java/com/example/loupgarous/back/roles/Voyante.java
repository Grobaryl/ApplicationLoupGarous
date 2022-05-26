package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Action;
import com.example.loupgarous.back.ActionType;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class Voyante extends Role {

    public Voyante(){
        super();
        this.team = Team.VILLAGE;
        this.roleNameId = R.string.role_seer_name;
        this.roleDescriptionId = R.string.role_seer_description;
        this.rolePicId = R.drawable.voyante;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

        Action a;
        if (targets.size() == 1){
            a = new Action(targets.get(0), ActionType.SAW);
            user.addAction(a);
        }
        else{
            a = new Action(targets.get(0),ActionType.SAW);
            user.addAction(a);
            a = new Action(targets.get(1),ActionType.SAW);
            user.addAction(a);
        }
    }


}
