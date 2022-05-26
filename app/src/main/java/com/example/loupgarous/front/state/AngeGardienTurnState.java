package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.back.roles.Metamorphe;
import com.example.loupgarous.front.PlayerChoiceFragment;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class AngeGardienTurnState extends TurnState {
    public AngeGardienTurnState(){
        this.choice = 1;
        this.rolename = "Anga";
        this.fragmentType = PlayerChoiceFragment.class;
        this.titleId = R.string.anga_turn;
        this.time = 15;
        this.soundFileId = R.raw.ange_gardien;;
    }

    @Override
    public TurnState nextState() {
        return new MetamorpheTurnState();
    }
}
