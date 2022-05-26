package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.back.roles.Mort;

public class MortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mort);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(Game.getInstance().isMortMorte()){
            Bundle args = new Bundle();
            args.putInt("choice",1);
            args.putString("role","Mort");
            transaction.add(R.id.fragment_container,PlayerChoiceFragment.class,args);
        }
        else{
            transaction.add(R.id.fragment_container,new SimpleTextFragment());

        }

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MortActivity.this.finish();
            }
        });
    }
}