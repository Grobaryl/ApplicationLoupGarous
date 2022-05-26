package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class ChouetteTurnState extends TurnState {
    public ChouetteTurnState(){
        this.choice = 1;
        this.rolename = "Chou";
        this.fragmentType = SideCardSelectionFragment.class;
        this.titleId = R.string.chou_turn;
        this.time = 18;
        this.soundFileId = R.raw.chouette;
    }

    @Override
    public TurnState nextState() {
        return new JokerTurnState();
    }
}
