package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;

public class PlayerNameSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name_selection);


        Button b = (Button)findViewById(R.id.nameValidation);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names[] = new String[5];
                EditText t;
                t = findViewById(R.id.player1name);
                names[0] = t.getText().toString();
                t = findViewById(R.id.player2name);
                names[1] = t.getText().toString();
                t = findViewById(R.id.player3name);
                names[2] = t.getText().toString();
                t = findViewById(R.id.player4name);
                names[3] = t.getText().toString();
                t = findViewById(R.id.player5name);
                names[4] = t.getText().toString();
                Game g = Game.getInstance();
                g.initGame(names);
                Intent i = new Intent(PlayerNameSelection.this,RoleResultActivity.class);
                PlayerNameSelection.this.startActivity(i);
            }
        });
    }
}