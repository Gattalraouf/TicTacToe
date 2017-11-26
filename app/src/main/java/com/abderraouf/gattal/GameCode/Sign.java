package com.abderraouf.gattal.GameCode;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import com.abderraouf.gattal.tictactoe.R;
import static com.abderraouf.gattal.GameCode.GameManager.board;

/**
 * Created by Orekei on 08/08/2017.
 */

public class Sign {
    static int sign=-1;
    static AnimationDrawable animation;
    static public void Draw( int xoro,int pos,ImageView o){

        if(xoro==1) {
            o.setImageResource(R.drawable.xanim);
            animation = (AnimationDrawable) o.getDrawable();
            animation.start();
            board[pos]=1;
            xoro=0;
        }
        else {
            o.setImageResource(R.drawable.oanim);
            animation = (AnimationDrawable) o.getDrawable();
            animation.start();
            board[pos]=0;
            xoro=1;
        }
    }
}
