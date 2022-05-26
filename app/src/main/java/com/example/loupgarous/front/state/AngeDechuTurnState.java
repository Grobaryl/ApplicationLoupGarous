package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.front.PlayerChoiceFragment;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class AngeDechuTurnState extends TurnState {
    public AngeDechuTurnState(){
        this.choice = 1;
        this.rolename = "Ande";
        this.fragmentType = PlayerChoiceFragment.class;
        this.titleId = R.string.ande_turn;
        this.time = 15;
        this.soundFileId = R.raw.ange_dechu;
    }

    @Override
    public TurnState nextState() {
        return new VoyanteTurnState();
    }
}
