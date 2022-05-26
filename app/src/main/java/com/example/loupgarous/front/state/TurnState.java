package com.example.loupgarous.front.state;


import androidx.fragment.app.Fragment;

public abstract class TurnState {

    protected String rolename;
    protected int choice;
    protected Class<? extends Fragment> fragmentType;
    protected int titleId;
    protected int time;
    protected int soundFileId;

    public abstract TurnState nextState();

    public String getRolename(){
        return this.rolename;
    }

    public int getChoice(){
        return this.choice;
    }

    public int getTitle(){return this.titleId;}
    public int getTimeMs(){return this.time*1000;}
    public int getTime(){return this.time;}
    public int getAudio(){return this.soundFileId;}

    public Class<? extends Fragment> getFragmentType(){
        return this.fragmentType;
    }
}
