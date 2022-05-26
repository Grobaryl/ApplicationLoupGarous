package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.back.Player;
import com.example.loupgarous.back.Recap;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Recap r = Game.getInstance().getWinner();
        TextView tv = (TextView)findViewById(R.id.winning_team);
        tv.setText(r.winningTeam.name());


        LinearLayout winnerLayout = (LinearLayout)findViewById(R.id.winner_layout);
        LinearLayout looserLayout = (LinearLayout)findViewById(R.id.looser_layout);

        for(Player p : r.winners){
            TextView mTextView = new TextView(this);
            mTextView.setTextSize(20);
            if(p.isDead())
                mTextView.setTextColor(Color.RED);
            mTextView.setText(p.getName()+ " "+getString(p.getRole().getRoleName()));
            winnerLayout.addView(mTextView);
        }

        for(Player p : r.loosers){
            TextView mTextView = new TextView(this);
            if(p.isDead())
                mTextView.setTextColor(Color.RED);
            mTextView.setText(p.getName()+ " "+getString(p.getRole().getRoleName()));
            looserLayout.addView(mTextView);
        }

    }
}