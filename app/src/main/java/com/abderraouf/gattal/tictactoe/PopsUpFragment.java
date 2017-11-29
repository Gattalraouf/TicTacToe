package com.abderraouf.gattal.tictactoe;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.abderraouf.gattal.GameCode.GameManager;
import static com.abderraouf.gattal.tictactoe.MainGameScreen.xoro;


public class PopsUpFragment extends Fragment implements View.OnClickListener{


    public PopsUpFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_pops_up, container, false);
        EditText playername = view.findViewById(R.id.playername);
        playername.setText(GameManager.sharedInfo.getString("mainPlayer","Player1"));
        TextView startplaying = view.findViewById(R.id.Confirm);
        final CheckBox x = view.findViewById(R.id.Xcheckbox);
        startplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlayer(view);
                if(x.isChecked()) xoro =1;
                else xoro = 0;
                if(GameManager.getFirstOpen()==1){
                    GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();
                    GameManager.setFirstOpen(0);
                }
                getFragmentManager().beginTransaction()
                        .remove(PopsUpFragment.this).commit();
            }
        });

        TextView Cancel = view.findViewById(R.id.Exit);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameManager.getFirstOpen()==1){
                    GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();
                    GameManager.setFirstOpen(0);
                }
                    getFragmentManager().beginTransaction()
                            .remove(PopsUpFragment.this).commit();
            }
        });
        LinearLayout lin = view.findViewById(R.id.linlayout);
        lin.setOnClickListener(this);
        addListener(view);
       /* RelativeLayout rLayout = view.findViewById(R.id.touchScreen);
        rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameManager.getFirstOpen()==1){
                    GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();
                    GameManager.setFirstOpen(0);
                }
                getFragmentManager().beginTransaction()
                        .remove(PopsUpFragment.this).commit();
            }
        });*/
        return view;
    }

    public void addListener(View view) {

        final CheckBox x = view.findViewById(R.id.Xcheckbox);
        final CheckBox o = view.findViewById(R.id.Ocheckbox);

        if(xoro==1) x.setChecked(true);
        else o.setChecked(true);
        o.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    x.setChecked(false);
                }
                else{
                    x.setChecked(true);
                }
            }
        });

        x.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    o.setChecked(false);
                }
                else{
                    o.setChecked(true);
                }
            }
        });

    }

    public void getPlayer(View view){

        final EditText playername = view.findViewById(R.id.playername);
        GameManager.sharedInfoEditor.putString("mainPlayer",playername.getText().toString()).apply();
        playername.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager)getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(playername.getWindowToken(), 0);

                    return true;

                }
                return false;
            }
        });
        return;
    }

    @Override
    public void onClick(View v) {

    }

}
