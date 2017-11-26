package com.abderraouf.gattal.GameCode;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.abderraouf.gattal.tictactoe.MainGameScreen;
import com.abderraouf.gattal.tictactoe.MainMenu;

/**
 * Created by Orekei on 08/08/2017.
 */

public class GameManager {

    static public int[] board ={2,2,2,2,2,2,2,2,2};
    static public int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    static public SharedPreferences sharedInfo = PreferenceManager.getDefaultSharedPreferences(MainMenu.getContextOfApplication());
    static private int firstOpen = sharedInfo.getInt("firstOpen",1);
    static private Player mainPlayer = new Player(sharedInfo.getString("playerName","player1"));
    static public SharedPreferences.Editor sharedInfoEditor = sharedInfo.edit();



    public static int getFirstOpen() {
        return firstOpen;
    }

    public static Player getMainPlayer() {
        return mainPlayer;
    }

    public static void setFirstOpen(int firstOpen) {
        GameManager.firstOpen = firstOpen;
    }

    public static void setMainPlayer(Player mainPlayer) {
        GameManager.mainPlayer = mainPlayer;
    }

}

