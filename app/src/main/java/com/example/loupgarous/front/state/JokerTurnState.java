package com.example.loupgarous.front.state;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.roles.AngeGardien;
import com.example.loupgarous.front.PlayerChoiceFragment;
import com.example.loupgarous.front.SideCardSelectionFragment;

public class JokerTurnState extends TurnState {
    public JokerTurnState(){
        this.choice = 2;
        this.rolename = "Joker";
        this.fragmentType = PlayerChoiceFragment.class;
        this.titleId = R.string.jokr_turn;
        this.time =20;
        this.soundFileId = R.raw.joker;
    }

    @Override
    public TurnState nextState() {
        return new AngeGardienTurnState();
    }

}
