package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Action;
import com.example.loupgarous.back.ActionType;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class Metamorphe extends Role {

    public Role power;

    public Metamorphe(){
        super();
        this.team = Team.LOUPGAROU;
        this.roleNameId = R.string.role_meta_name;
        this.roleDescriptionId = R.string.role_meta_description;
        this.rolePicId = R.drawable.metamorphe;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

        Action a;
        if (targets.get(0).getClass() == Player.class){
            a = new Action(targets.get(0), ActionType.KILLED);
            user.addAction(a);
        }
        else{
            a = new Action(targets.get(0),ActionType.TRANSFORMED);
            user.addAction(a);
        }
    }

}
