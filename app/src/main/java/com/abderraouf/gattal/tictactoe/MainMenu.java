package com.abderraouf.gattal.tictactoe;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity implements View.OnClickListener, PopsUpFragment.OnFragmentInteractionListener {

    public void Play(View view){


        MainGameScreen.vsComputer=false;

        Fragment frag;
        FragmentManager fm1 = MainMenu.this
                .getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        frag = new PopsUpFragment();
        ft1.add(R.id.Popsup, frag);
        ft1.commit();


        //startActivity(new Intent(getApplicationContext(),MainGameScreen.class));
    }

    public void PlayAlone(View view){
        MainGameScreen.vsComputer=true;
       /* Fragment pop_up = new PopsUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

*/
        startActivity(new Intent(getApplicationContext(),MainGameScreen.class));
    }

    public void Exit(View view){

        this.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}