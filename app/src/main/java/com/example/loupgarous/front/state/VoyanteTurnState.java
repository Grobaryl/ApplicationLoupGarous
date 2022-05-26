package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class VoyanteTurnState extends TurnState {
    public VoyanteTurnState(){
        this.choice = 2;
        this.rolename = "Seer";
        this.fragmentType = SideCardSelectionFragment.class;
        this.titleId = R.string.seer_turn;
        this.time = 15;
        this.soundFileId = R.raw.voyante;
    }

    @Override
    public TurnState nextState() {
        return null;
    }
}
