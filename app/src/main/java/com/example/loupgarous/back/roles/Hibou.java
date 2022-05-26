package com.example.loupgarous.back.roles;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Actable;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.Team;

import java.util.List;

public class Hibou extends Role {

    public Hibou(){
        super();
        this.team = Team.VILLAGE;
        this.roleNameId = R.string.role_hibu_name;
        this.roleDescriptionId = R.string.role_hibu_description;
        this.rolePicId = R.drawable.hibou;
    }

    @Override
    public void usePower(Player user, List<Actable> targets) {

    }
}
