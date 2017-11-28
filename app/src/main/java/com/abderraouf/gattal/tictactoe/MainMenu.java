package com.abderraouf.gattal.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.abderraouf.gattal.GameCode.GameManager;

public class MainMenu extends AppCompatActivity implements PopsUpFragment.OnFragmentInteractionListener {

    public static Context contextOfApplication;

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    public void Play(View view){
        MainGameScreen.vsComputer=false;
        startActivity(new Intent(view.getContext(),MainGameScreen.class));
    }

    public void PlayAlone(View view){
        MainGameScreen.vsComputer=true;
        startActivity(new Intent(getApplicationContext(),MainGameScreen.class));
    }

    public void Exit(View view){
        onDestroy();
    }

    public void Settings(View view){
        Fragment frag;
        FragmentManager fm1 = MainMenu.this
                .getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        frag = new PopsUpFragment();
        ft1.add(R.id.Popsup, frag);
        ft1.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        contextOfApplication = getApplicationContext();

        if(GameManager.getFirstOpen()==1){

            Fragment frag;
            FragmentManager fm1 = MainMenu.this
                    .getSupportFragmentManager();
            FragmentTransaction ft1 = fm1.beginTransaction();
            frag = new PopsUpFragment();
            ft1.add(R.id.Popsup, frag);
            ft1.commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finishAffinity();
    }

}