package com.example.loupgarous.front;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.back.Player;

public class RoleResultActivity extends AppCompatActivity {


    public int currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_result);
        this.currentPlayer = 0;
        this.setData(false);
        Button b = (Button) findViewById(R.id.ok_role);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer++;
                if(currentPlayer <= 4) {
                    setData(true);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RoleResultActivity.this);
                    builder1.setMessage(getString(R.string.pass_to_player) + Game.getInstance().getPlayer(currentPlayer).getName());
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    setData(false);
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else{
                    Intent i = new Intent(RoleResultActivity.this,NightActivity.class);
                    RoleResultActivity.this.startActivity(i);
                }
            }
        });
    }


    public void setData(boolean clear){

        Player p = Game.getInstance().getPlayer(currentPlayer);

        ImageView im = (ImageView)(findViewById(R.id.role_image));
        im.setImageResource(clear?R.drawable.dos:p.getRole().getRolePic());

        TextView t = (TextView) findViewById(R.id.player_name_role);
        t.setText(clear?"":p.getName());

        t = (TextView) findViewById(R.id.rolename);
        t.setText(clear?"":getString(p.getRole().getRoleName()));

        System.out.println(t.getText());

        t = (TextView) findViewById(R.id.role_description);
        t.setText(clear?"":getString(p.getRole().getRoleDesc()));


    }
}