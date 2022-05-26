package com.example.loupgarous.front.state;

import android.view.WindowInsets;

import com.example.loupgarous.R;
import com.example.loupgarous.back.roles.Chouette;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class TransformTurnState extends TurnState {

    public TransformTurnState(){
        this.choice = 1;
        this.rolename = "Meta";
        this.fragmentType = SideCardSelectionFragment.class;
        this.titleId = R.string.meta_turn;
        this.time = 16;
        this.soundFileId = R.raw.meta_tf;;
    }


    @Override
    public TurnState nextState() {
        return new ChouetteTurnState();
    }

}
