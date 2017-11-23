package com.abderraouf.gattal.tictactoe;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.abderraouf.gattal.GameCode.ComputerAi;
import com.abderraouf.gattal.GameCode.GameManager;
import com.abderraouf.gattal.GameCode.Player;
import static java.lang.Math.abs;

public class MainGameScreen extends AppCompatActivity implements View.OnClickListener,PopsUpFragment.OnFragmentInteractionListener {
    static boolean vsComputer ;
    static Player player1 = new Player("Player 1");
    static Player player2 = new Player("Computer");
    boolean begins=true;
    static int xoro = 1;
    AnimationDrawable animation;
    boolean won = false;
    //int[] board ={2,2,2,2,2,2,2,2,2};
    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int score[]={0,0,0};
    int xo=xoro;
/*
    public void addListener() {
        final CheckBox x = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox o = (CheckBox) findViewById(R.id.checkBox);
        x.setChecked(true);
        o.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    xoro=0;
                    x.setChecked(false);
                }
                else{
                    xoro=1;
                    x.setChecked(true);
                }
            }
        });
        x.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    xoro=1;
                    o.setChecked(false);
                }
                else{
                    xoro=0;
                    o.setChecked(true);
                }
            }
        });

    }

    public void Confirm(View view){
        EditText playername = (EditText)findViewById(R.id.playerName);
        EditText playername2 = (EditText)findViewById(R.id.playerName2);
        Player secondPlayer;
        String player2;
        String player1;
        Player player = new Player(playername.getText().toString());
        if(vsComputer) {
            secondPlayer = new ComputerAi();
            begins=false;
        }
        else secondPlayer = new Player(playername2.getText().toString());
        player1= player.getPlayerName();
        player2= secondPlayer.getPlayerName();
        for(int i= 0;i<(18-2*player.getPlayerName().length());i++){
            player1=player1+" ";
        }
        TextView playersNames = (TextView) findViewById(R.id.players);
        if(begins){
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.windowlayout);
            linearLayout.setVisibility(View.GONE);
            LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.secondWindow);
            linearLayout1.setVisibility(View.VISIBLE);
            begins=false;
        }
        else{
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.WinOrLose);
            //relativeLayout.animate().alpha(0f);
            for(int j=0;j<relativeLayout.getChildCount();j++){
                relativeLayout.getChildAt(j).setEnabled(false);
            }
            relativeLayout.setVisibility(View.GONE);

            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.windowlayout);
            linearLayout.setClickable(true);
            //relativeLayout.setClickable(false);
            ImageView img = (ImageView) findViewById(R.id.window);
            img.setVisibility(View.GONE);
            LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.secondWindow);
            linearLayout1.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            playersNames.setText(player1+"                    "+player2);
            GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
            for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
                gridLayout.getChildAt(i).setClickable(true);
            }
        }


    }

    */
    public void O(View view){
        final GridLayout gridLayout =(GridLayout)findViewById(R.id.grid) ;
        final Handler handler = new Handler();
        final ComputerAi comp = new ComputerAi();
        ImageView o = (ImageView) view;
        int pos = Integer.parseInt(o.getTag().toString());

        if(GameManager.board[pos]==2){
            //int result = WinLoseNull();//checking the state of game
            if(xo==1) {//chose x to play with
                o.setImageResource(R.drawable.xanim);
                if(!vsComputer) xo=0;
            }
            else {//chose O to play with
                o.setImageResource(R.drawable.oanim);
                if(!vsComputer) xo=1;
            }
            animation = (AnimationDrawable) o.getDrawable();
            animation.start();
            if(!vsComputer)GameManager.board[pos]=abs(xo-1);
            else GameManager.board[pos]=xo;
            int result = WinLoseNull();
            if(result==-1&&vsComputer){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        comp.ComputerPlay(abs(xoro-1),gridLayout);
                    }
                },1000);
            }
            //result = WinLoseNull();
            /*if(result!=-1) {
                for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
                    gridLayout.getChildAt(i).setClickable(false);
                }
                score[result]++;
                TextView scorex = (TextView) findViewById(R.id.scorex);
                scorex.setText(Integer.toString(score[1]));
                TextView scoreo = (TextView) findViewById(R.id.scoreo);
                scoreo.setText(Integer.toString(score[0]));
                ImageView winorlose = (ImageView) findViewById(R.id.WinLose);
                if(xoro==result)winorlose.setImageResource(R.drawable.youwin);
                else if(result==2)winorlose.setImageResource(R.drawable.nullgame);
                else winorlose.setImageResource(R.drawable.youlose);
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.WinOrLose);
                for(int j=0;j<relativeLayout.getChildCount();j++){
                    relativeLayout.getChildAt(j).setEnabled(true);
                }
                relativeLayout.setVisibility(View.VISIBLE);
                //relativeLayout.animate().alpha(1f).setDuration(200);

            }*/
        }
                    /*Animation zoomAnimation,slideUpAnimation, slideDownAnimation,slideLeftAnimation,slideRightAnimation,slideUpLeftAnimation, slideDownLeftAnimation,slideUpRightAnimation, slideDownRightAnimation;

                    slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.toup);

                    slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.todown);

                    slideLeftAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toleft);

                    slideDownLeftAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.todownleft);

                    slideUpRightAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toupright);

                    slideDownRightAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.todownright);

                    slideUpLeftAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toupleft);

                    slideRightAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.toright);

                    zoomAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);

                    ImageView a = (ImageView) findViewById(R.id.imageView2);
                    ImageView b = (ImageView) findViewById(R.id.imageView8);
                    ImageView c = (ImageView) findViewById(R.id.Oooo);
                    ImageView d = (ImageView) findViewById(R.id.imageView6);

                    b.startAnimation(slideDownLeftAnimation);
                    c.startAnimation(slideDownRightAnimation);
                    b.animate().alpha(0f).setDuration(1500);
                    c.animate().alpha(0f).setDuration(1500);
                    d.startAnimation(zoomAnimation);

                    //a.startAnimation(slideUpAnimation);*/




    }

    public void Exit(View view){
        Intent i = new Intent(getApplicationContext(),MainMenu.class);
        startActivity(i);
    }

    public int WinLoseNull(){
        int result=-1;
        for(int[] poswin:winpos) {
            if(GameManager.board[poswin[0]]==GameManager.board[poswin[1]]&&GameManager.board[poswin[2]]==GameManager.board[poswin[1]]&&GameManager.board[poswin[0]]!=2){
                //winner case
                result=GameManager.board[poswin[0]];
                won=true;
            }
            else{
                boolean nul=false;
                for(int j=0;j<9;j++){
                    if(GameManager.board[j]==2) nul=true;
                }
                if(!nul&&!won) {
                    result= 2;
                }
            }
        }
        return result;
    }

    /*
    public void playAgain(View view){
        xo=xoro;
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
        for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
            gridLayout.getChildAt(i).setClickable(true);
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.WinOrLose);
        for(int j=0;j<relativeLayout.getChildCount();j++){
            relativeLayout.getChildAt(j).setEnabled(false);
        }
        relativeLayout.setVisibility(View.GONE);
        for(int j=0;j<9;j++){
            GameManager.board[j]=2;
        }
        for(int i = 0 ; i <gridLayout.getChildCount() ; i++){
            //gridLayout.getChildAt(i).setClickable(true);
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setImageResource(0);
        }
        won=false;
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_screen);
        //RelativeLayout lin = (RelativeLayout) findViewById(R.id.WinOrLose);
        //lin.setOnClickListener(this);
        //addListener();
       /* GridLayout gridLayout = (GridLayout)findViewById(R.id.grid);
        for(int i = 0 ; i < gridLayout.getChildCount() ; i++){
            gridLayout.getChildAt(i).setClickable(false);
        }*/

        GridLayout gl = (GridLayout)findViewById(R.id.grid);
        ViewTreeObserver vto = gl.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {@Override public void onGlobalLayout()
        {

            GridLayout gl = (GridLayout) findViewById(R.id.grid);
            fillview(gl);

            ViewTreeObserver obs = gl.getViewTreeObserver();
            obs.removeGlobalOnLayoutListener(this);
        }});

    }

    public void fillview(GridLayout gl)
    {
        ImageView img;

        int idealChildWidth = (int) (gl.getWidth()/gl.getColumnCount());
        int idealChildHeigth=(int) (gl.getHeight()/gl.getColumnCount());
        for( int i=0; i< gl.getChildCount();i++)
        {
            img = (ImageView) gl.getChildAt(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
                    final Handler handler = new Handler();
                    final ComputerAi comp = new ComputerAi();
                    ImageView o = (ImageView) gridLayout.getChildAt(gridLayout.indexOfChild(v));
                    int pos = Integer.parseInt(o.getTag().toString());

                    if (GameManager.board[pos] == 2) {
                        //int result = WinLoseNull();//checking the state of game
                        if (xo == 1) {//chose x to play with
                            o.setImageResource(R.drawable.xanim);
                            if (!vsComputer) xo = 0;
                        } else {//chose O to play with
                            o.setImageResource(R.drawable.oanim);
                            if (!vsComputer) xo = 1;
                        }
                        animation = (AnimationDrawable) o.getDrawable();
                        animation.start();
                        if (!vsComputer) GameManager.board[pos] = abs(xo - 1);
                        else GameManager.board[pos] = xo;
                        int result = WinLoseNull();
                        if (result == -1 && vsComputer) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    comp.ComputerPlay(abs(xoro - 1), gridLayout);
                                }
                            }, 1000);
                        }
                    }
                    System.out.println("Hey Someone just clicked me, image View number: "+gridLayout.indexOfChild(v));
                }
            });
            img.getLayoutParams().width=(idealChildWidth);
            img.getLayoutParams().height=idealChildHeigth;
        }
    }

    @Override
    public void onClick(View v) {
       /* if(v.getId()==R.id.WinOrLose){
            InputMethodManager in =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }*/
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}