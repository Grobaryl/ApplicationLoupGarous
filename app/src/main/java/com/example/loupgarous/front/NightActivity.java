package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.front.state.TransformTurnState;
import com.example.loupgarous.front.state.TurnState;

public class NightActivity extends AppCompatActivity {

    private int count;
    private TurnState state;
    private CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);
        FragmentContainerView f = (FragmentContainerView)findViewById(R.id.fragment_container);
        this.state = new TransformTurnState();
        runNextFragment();
    }

    private void runNextFragment(){
        if(state == null){
            for(int i=0;i<5;i++){
                //System.out.println(Game.getInstance().getPlayer(i));
            }
            Intent i = new Intent(this, DebriefActivity.class);
            this.startActivity(i);
            return;
        }
        TextView textView = (TextView)findViewById(R.id.count_down_text);
        count = state.getTime();
        cdt = new CountDownTimer(state.getTimeMs(), 1000){
            public void onTick(long millisUntilFinished){
                textView.setText(String.valueOf(count));
                count--;
            }
            public  void onFinish(){
                textView.setText("FINISH!!");
                runNextFragment();
            }
        };
        TextView t = (TextView)findViewById(R.id.role_turn);
        t.setText(state.getTitle());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle args = new Bundle();
        args.putInt("choice",state.getChoice());
        args.putString("role",state.getRolename());
        transaction.replace(R.id.fragment_container,state.getFragmentType(),args);
        transaction.addToBackStack(null);
        transaction.commit();
        cdt.start();
        MediaPlayer m = MediaPlayer.create(this,state.getAudio());
        m.start();
        System.out.println("Turn of "+state.getRolename());
        this.state = state.nextState();
    }
}