package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity {


    private int currentVoter;
    private List<RadioButton> radioButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        currentVoter = 0;

        while(Game.getInstance().getPlayer(currentVoter).isDead()){
            currentVoter++;
        }

        TextView tv = (TextView)findViewById(R.id.nom_votant);
        tv.setText(Game.getInstance().getPlayer(currentVoter).getName());
        radioButtonList = new ArrayList<>();
        radioButtonList.add(findViewById(R.id.radio_p0));
        radioButtonList.add(findViewById(R.id.radio_p1));
        radioButtonList.add(findViewById(R.id.radio_p2));
        radioButtonList.add(findViewById(R.id.radio_p3));
        radioButtonList.add(findViewById(R.id.radio_p4));
        for(int i=0;i<5;i++){
            radioButtonList.get(i).setText(Game.getInstance().getPlayer(i).getName());
            if(Game.getInstance().getPlayer(i).isDead())
                radioButtonList.get(i).setTextColor(Color.RED);
        }

        Button b = (Button) findViewById(R.id.submit_vote);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoteActivity.this.submitVote();
            }
        });

    }

    private void submitVote(){
        for(int i=0;i<5;i++){
            if(radioButtonList.get(i).isChecked()){
                if(Game.getInstance().voteFor(currentVoter,i)){
                    this.currentVoter++;
                    while(currentVoter<5&&Game.getInstance().getPlayer(currentVoter).isDead()){
                        currentVoter++;
                    }
                    ((RadioGroup)findViewById(R.id.rad_group)).clearCheck();
                    if(currentVoter>=5){
                        this.startActivity(new Intent(this,ResultActivity.class));
                    }
                    else
                        ((TextView)findViewById(R.id.nom_votant)).setText(Game.getInstance().getPlayer(currentVoter).getName());
                }

            }
        }
    }
}