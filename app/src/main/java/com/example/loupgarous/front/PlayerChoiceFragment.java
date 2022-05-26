package com.example.loupgarous.front;

import android.graphics.Color;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SideCardSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerChoiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NB_CHOICE = "choice";
    private static final String ARG_ROLE = "role";

    private int choice;
    private String role;
    private List<TextView> names;
    private String firstChoice;

    public PlayerChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SideCardSelectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SideCardSelectionFragment newInstance(int param1, String param2) {
        SideCardSelectionFragment fragment = new SideCardSelectionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NB_CHOICE, param1);
        args.putString(ARG_ROLE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){

        this.names = new ArrayList<>();
        if (getArguments() != null) {
            choice = getArguments().getInt(ARG_NB_CHOICE);
            role = getArguments().getString(ARG_ROLE);
        }

        TextView tv = (TextView)getView().findViewById(R.id.player_name_0);
        tv.setText(Game.getInstance().getPlayer(0).getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoiceFragment.this.selectCard(0);
            }
        });
        names.add(tv);
        tv = (TextView)getView().findViewById(R.id.player_name_1);
        tv.setText(Game.getInstance().getPlayer(1).getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoiceFragment.this.selectCard(1);
            }
        });
        names.add(tv);
        tv = (TextView)getView().findViewById(R.id.player_name_2);
        tv.setText(Game.getInstance().getPlayer(2).getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoiceFragment.this.selectCard(2);
            }
        });
        names.add(tv);
        tv = (TextView)getView().findViewById(R.id.player_name_3);
        tv.setText(Game.getInstance().getPlayer(3).getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoiceFragment.this.selectCard(3);
            }
        });
        names.add(tv);

        tv = (TextView)getView().findViewById(R.id.player_name_4);
        tv.setText(Game.getInstance().getPlayer(4).getName());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoiceFragment.this.selectCard(4);
            }
        });
        names.add(tv);

        for(int i=0;i<5;i++){
            if(Game.getInstance().getPlayer(i).isDead()){
                names.get(i).setTextColor(Color.RED);
            }
        }
    }

    private void selectCard(int index){
        switch (role){
            case "Meta":
                if(choice>0) {
                    choice--;
                    Game.getInstance().playMetamorphe(names.get(index).getText().toString());
                    names.get(index).setTextColor(Color.RED);
                }
                break;
            case "Anga":
                if(choice>0) {
                    choice--;
                    Game.getInstance().playAngeG(names.get(index).getText().toString());
                    names.get(index).setTextColor(Color.BLUE);
                }
                break;
            case "Ande":
                if(choice>0&&!Game.getInstance().getPlayer(index).isDead()) {
                    choice--;
                    Game.getInstance().playAngeD(names.get(index).getText().toString());
                    names.get(index).setTextColor(Color.GREEN);
                }
                break;
            case "Mort":
                if(choice>0&&!Game.getInstance().getPlayer(index).isDead()) {
                    choice--;
                    Game.getInstance().playMort(names.get(index).getText().toString());
                    names.get(index).setTextColor(Color.GREEN);
                }
                break;
            case "Joker":
                if(choice == 2){
                    choice --;
                    names.get(index).setTextColor(Color.MAGENTA);
                    firstChoice = names.get(index).getText().toString();
                }
                else {

                    if(choice==1) {
                        choice--;
                        Game.getInstance().playJoker(firstChoice, names.get(index).getText().toString());
                        names.get(index).setTextColor(Color.MAGENTA);
                    }
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_select, container, false);
    }
}