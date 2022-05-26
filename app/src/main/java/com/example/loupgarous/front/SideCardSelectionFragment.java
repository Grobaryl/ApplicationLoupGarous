package com.example.loupgarous.front;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loupgarous.R;
import com.example.loupgarous.back.Game;
import com.example.loupgarous.back.Role;
import com.example.loupgarous.back.roles.Chouette;
import com.example.loupgarous.back.roles.Hibou;
import com.example.loupgarous.back.roles.Metamorphe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SideCardSelectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SideCardSelectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NB_CHOICE = "choice";
    private static final String ARG_ROLE = "role";

    private int choice;
    private String role;
    private List<ImageView> images;
    private int firstChoice;

    public SideCardSelectionFragment() {
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.images = new ArrayList<>();
        if (getArguments() != null) {
            choice = getArguments().getInt(ARG_NB_CHOICE);
            role = getArguments().getString(ARG_ROLE);
        }
        ImageView img = (ImageView)getView().findViewById(R.id.card_select_0);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SideCardSelectionFragment.this.selectCard(0);
            }
        });
        images.add(img);

        img = (ImageView)getView().findViewById(R.id.card_select_1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SideCardSelectionFragment.this.selectCard(1);
            }
        });
        images.add(img);
        img = (ImageView)getView().findViewById(R.id.card_select_2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SideCardSelectionFragment.this.selectCard(2);
            }
        });
        images.add(img);
        img = (ImageView)getView().findViewById(R.id.card_select_3);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SideCardSelectionFragment.this.selectCard(3);
            }
        });
        images.add(img);
    }

    private void selectCard(int index){
        switch (role){
            case "Meta":
                if(choice>0&&Game.getInstance().containsRole(Metamorphe.class)) {
                    choice--;
                    Role transformed = Game.getInstance().playTransform(index);
                    images.get(index).setImageResource(transformed.getRolePic());
                }
                break;
            case "Chou":
                if(choice>0&&Game.getInstance().containsRole(Chouette.class)&&!Game.getInstance().containsRole(Hibou.class)) {
                    choice--;
                    Role r = Game.getInstance().playChouette(index);
                    images.get(index).setImageResource(r.getRolePic());
                }
                break;
            case "Seer":
                if(choice == 2&&Game.getInstance().containsRole(Chouette.class)){
                    choice --;
                    images.get(index).setAlpha(0.5f);
                    firstChoice = index;
                }
                else {
                    if(choice ==1&&index!=firstChoice) {
                        choice--;
                        List<Role> l = Game.getInstance().playVoyante(firstChoice, index);
                        if(l==null)
                            break;
                        images.get(firstChoice).setAlpha(1.0f);
                        images.get(index).setImageResource(l.get(0).getRolePic());
                        images.get(firstChoice).setImageResource(l.get(1).getRolePic());
                    }
                }
                break;
            default:
                System.out.println("eep");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side_card_selection, container, false);

    }
}