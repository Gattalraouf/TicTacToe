package com.abderraouf.gattal.GameCode;


import android.widget.GridLayout;
import android.widget.ImageView;
import java.util.Random;
import static com.abderraouf.gattal.GameCode.GameManager.board;
import static java.lang.Math.abs;

/**
 * Created by Orekei on 08/08/2017.
 */

public class ComputerAi extends Player {
    int corners[]={0,2,6,8};


    public ComputerAi() {
        super("Bot");
    }

    public int WinOrBlock(boolean winOrBlock,int signType){
        int win=-1;
        for(int[] poswin: GameManager.winpos)
        {
            if(GameManager.board[poswin[0]]== GameManager.board[poswin[1]]&&GameManager.board[poswin[2]]==2&&GameManager.board[poswin[0]]!=2){
               win=poswin[2];
                if(GameManager.board[poswin[0]]==signType){
                    if(winOrBlock)break;
                }
                else{
                    if(!winOrBlock)break;
                }
            }
            else if(GameManager.board[poswin[0]]== GameManager.board[poswin[2]]&&GameManager.board[poswin[1]]==2&&GameManager.board[poswin[0]]!=2){
                win=poswin[1];
                if(GameManager.board[poswin[0]]==signType){
                    if(winOrBlock)break;
                }
                else{
                    if(!winOrBlock)break;
                }
            }
            else if(GameManager.board[poswin[2]]== GameManager.board[poswin[1]]&&GameManager.board[poswin[0]]==2&&GameManager.board[poswin[1]]!=2){
                win=poswin[0];
                if(GameManager.board[poswin[1]]==signType){
                    if(winOrBlock)break;
                }
                else{
                    if(!winOrBlock)break;
                }
            }
        }
        return win;
    }

    public boolean EmptyCenter(){
        if( GameManager.board[4]==2) return true;
        else return false;
    }

    public int OppositeCorner(int otherSignType){
        int cornerPos=-1;

        int j=3;
        for(int i=0;i<corners.length;i++){
            if (GameManager.board[corners[i]]==otherSignType&&GameManager.board[corners[j]]==2) {
                cornerPos=corners[j];
            }
            j--;
        }
        return cornerPos;
    }

    public int EmptyCorner(){
        int cornerPos=-1;

        for(int i=0;i<corners.length;i++){
            if (GameManager.board[corners[i]]==2) {
                cornerPos=corners[i];
                break;
            }
        }
        return cornerPos;
    }

    public int EmptySide(){
        int SidePos=-1;

        for(int i=1;i<8;i+=2){
            if (board[i]==2) {
                SidePos=i;
                break;
            }
        }
        return SidePos;
    }

    public void ComputerPlay(int SignType, GridLayout grid){
        ImageView view;
        int i;
        int j=abs(SignType-1);
        if(GameManager.board[4]==j) {
            if((i=WinOrBlock(true,SignType))!=-1){//know if it is a win play and do it ;)
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else if((i=WinOrBlock(false,SignType))!=-1){//know if it is a block and do it ;)
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else{
                Random random = new Random();
                i=random.nextInt(8);
                while(board[i]!=2){
                    i=random.nextInt(8);
                }
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
        }
        else {
            if((i=WinOrBlock(true,SignType))!=-1){//know if it is a win play and do it ;)
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else if((i=WinOrBlock(false,SignType))!=-1){//know if it is a block and do it ;)
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else if(EmptyCenter()){//check the center
                view = (ImageView) grid.getChildAt(4);
                Sign.Draw(SignType,4,view);
            }
            else if((i=OppositeCorner(j))!=-1){
                //draw the sign in the pos i
                //opposite corner
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else if((i=EmptyCorner())!=-1){
                //draw in empty corner pos i
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
            else if((i=EmptySide())!=-1){
                //draw in empty side pos i
                view = (ImageView) grid.getChildAt(i);
                Sign.Draw(SignType,i,view);
            }
        }
    }
}
