package com.abderraouf.gattal.tictactoe;


import android.content.Context;
import android.net.Uri;
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
import com.abderraouf.gattal.GameCode.Player;
import static com.abderraouf.gattal.tictactoe.MainGameScreen.xoro;


public class PopsUpFragment extends Fragment implements View.OnClickListener{

    boolean begins=true;
    int i=1;
    private OnFragmentInteractionListener mListener;

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
        startplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPlayer(view);
                GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();
                GameManager.setFirstOpen(0);

                getFragmentManager().beginTransaction()
                        .remove(PopsUpFragment.this).commit();
            }
        });

        TextView Cancel = view.findViewById(R.id.Exit);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();
                GameManager.setFirstOpen(0);
                    getFragmentManager().beginTransaction()
                            .remove(PopsUpFragment.this).commit();

            }
        });

        LinearLayout lin = view.findViewById(R.id.linlayout);
        lin.setOnClickListener(this);
        addListener(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void addListener(View view) {

        final CheckBox x = view.findViewById(R.id.Xcheckbox);
        final CheckBox o = view.findViewById(R.id.Ocheckbox);

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

    public void getPlayer(View view){

        final EditText playername = view.findViewById(R.id.playername);
        GameManager.sharedInfoEditor.putString("mainPlayer",playername.getText().toString()).apply();
        GameManager.setMainPlayer(new Player(playername.getText().toString()));
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
