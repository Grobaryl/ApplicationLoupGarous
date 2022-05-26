package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.back.roles.AngeDechu;
import com.example.loupgarous.front.PlayerChoiceFragment;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class MetamorpheTurnState extends TurnState {
    public MetamorpheTurnState(){
        this.choice = 1;
        this.rolename = "Meta";
        this.fragmentType = PlayerChoiceFragment.class;
        this.titleId = R.string.meta_turn;
        this.time = 20;
        this.soundFileId = R.raw.meta;

    }

    @Override
    public TurnState nextState() {
        return new AngeDechuTurnState();
    }
}
