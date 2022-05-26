package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.roles.Mort;

import org.w3c.dom.Text;

public class DebriefActivity extends AppCompatActivity {


    private boolean mortDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_debrief);
        Button b = (Button)findViewById(R.id.vote_button);
        mortDone = !Game.getInstance().containsRole(Mort.class);
        if(mortDone){
            b.setText(R.string.mort_call);
        }
        else {
            b.setText(R.string.start_vote);
        }


        super.onCreate(savedInstanceState);

        Player p = Game.getInstance().getEaten();
        TextView tv = (TextView) findViewById(R.id.dead_name);
        if (p == null)
            tv.setText(R.string.none);
        else
            tv.setText(p.getName());

        Chronometer chronometer = (Chronometer)findViewById(R.id.chrono) ;
        chronometer.start();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DebriefActivity.this.mortDone) {
                    Intent i = new Intent(DebriefActivity.this, VoteActivity.class);
                    DebriefActivity.this.startActivity(i);
                }
                else{
                    DebriefActivity.this.mortDone = true;
                    b.setText(R.string.start_vote);
                    Intent i = new Intent(DebriefActivity.this, MortActivity.class);
                    DebriefActivity.this.startActivity(i);
                }
            }
        });
    }
}