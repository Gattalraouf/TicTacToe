package com.abderraouf.gattal.tictactoe;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.abderraouf.gattal.GameCode.ComputerAi;
import com.abderraouf.gattal.GameCode.GameManager;
import com.abderraouf.gattal.GameCode.Player;
import static java.lang.Math.abs;

public class MainGameScreen extends AppCompatActivity implements
        WinOrLoseFragment.FragmentCommunicator,
        WinOrLoseFragment.FragmentResultDetector{
    static boolean vsComputer ;
    static Player player2 = new Player("Bot");
    static int xoro = 1;
    int resu=2;
    AnimationDrawable animation;
    boolean won = false;
    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    static int xo=xoro;

    public int WinLoseNull(){
        int result=-1;
        for(int[] poswin:winpos) {
            if(GameManager.board[poswin[0]]==GameManager.board[poswin[1]]&&GameManager.board[poswin[2]]==GameManager.board[poswin[1]]&&GameManager.board[poswin[0]]!=2){
                result=GameManager.board[poswin[0]];
                won=true;
            }
        }
        if(!won) {
            boolean nul=false;
            for(int j=0;j<9;j++){
                if(GameManager.board[j]==2) nul=true;
            }
            if(!nul&&!won) {
                result= 2;
            }
            else {
                updateScore(result);
            }
        }
        if(result!=-1) callWinLoseFragment(result);
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_screen);
        reinitializeGame();
        TextView Player1 = findViewById(R.id.player1);
        TextView Player2 = findViewById(R.id.player2);
        Player1.setText(GameManager.sharedInfo.getString("mainPlayer","Player1"));
        if(!vsComputer) player2.setPlayerName("Guest");
        Player2.setText(player2.getPlayerName());

        GridLayout gl = findViewById(R.id.grid);
        ViewTreeObserver vto = gl.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {@Override public void onGlobalLayout()
        {

            GridLayout gl = findViewById(R.id.grid);
            fillview(gl);

            ViewTreeObserver obs = gl.getViewTreeObserver();
            obs.removeGlobalOnLayoutListener(this);
        }});

    }

    public void fillview(final GridLayout gl) {
        ImageView img;
        int idealChildWidth =  (gl.getWidth()/gl.getColumnCount());
        int idealChildHeigth= (gl.getHeight()/gl.getColumnCount());
        for( int i=0; i< gl.getChildCount();i++)
        {
            img = (ImageView) gl.getChildAt(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final GridLayout gridLayout = findViewById(R.id.grid);
                    final FrameLayout issue = findViewById(R.id.MultiClickIssue);
                    if(vsComputer) issue.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    final ComputerAi comp = new ComputerAi();
                    ImageView o = (ImageView) gridLayout.getChildAt(gridLayout.indexOfChild(v));
                    int pos = Integer.parseInt(o.getTag().toString());

                    if (GameManager.board[pos] == 2) {
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
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            WinLoseNull();
                                        }
                                    }, 300);
                                    issue.setVisibility(View.GONE);
                                }
                            }, 1000);
                        }

                    }
                }
            });
            img.getLayoutParams().width=(idealChildWidth);
            img.getLayoutParams().height=idealChildHeigth;
        }
    }

    public void updateScore(int res){
        if(res==1){
            TextView score = findViewById(R.id.XScore);
            int i = Integer.parseInt(score.getText().toString());
            Log.i("check","scoreX = "+i);
            i++;
            if(i<10)score.setText("0"+i);
            else score.setText(""+i);
        }
        else if(res==0){
            TextView score = findViewById(R.id.OScore);
            int i = Integer.parseInt(score.getText().toString());
            i++;
            if(i<10)score.setText("0"+i);
            else score.setText(""+i);
        }
    }

    public void callWinLoseFragment(int res){
        resu=res;
        WinOrLoseFragment frag;
        FragmentManager fm1 = MainGameScreen.this
                .getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        frag = new WinOrLoseFragment();
        ft1.add(R.id.WinLoseFragment, frag);
        ft1.commit();
    }

    public void reinitializeGame(){
        GameManager.board = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    }

    @Override
    public void fragmentDetached() {
        updateScore(resu);
        xo=xoro;
        won=false;
        GameManager.board = new int[] {2,2,2,2,2,2,2,2,2};
        FrameLayout issue = findViewById(R.id.MultiClickIssue);
        issue.setVisibility(View.GONE);
        GridLayout gl = findViewById(R.id.grid);
        for(int i=0;i<gl.getChildCount();i++){
            ImageView img = (ImageView) gl.getChildAt(i);
            img.setImageResource(0);
        }
    }

    @Override
    public int getResult() {
        return resu;
    }
}