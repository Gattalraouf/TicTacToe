package com.abderraouf.gattal.tictactoe;


import android.content.Context;
import android.content.Intent;
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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.abderraouf.gattal.GameCode.ComputerAi;
import com.abderraouf.gattal.GameCode.GameManager;
import com.abderraouf.gattal.GameCode.Player;

import java.net.URLConnection;

import static android.content.Context.TEXT_SERVICES_MANAGER_SERVICE;
import static com.abderraouf.gattal.tictactoe.MainGameScreen.vsComputer;
import static com.abderraouf.gattal.tictactoe.MainGameScreen.xoro;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PopsUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PopsUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopsUpFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    boolean begins=true;
    int i=1;
    private OnFragmentInteractionListener mListener;

    public PopsUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopsUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopsUpFragment newInstance(String param1, String param2) {
        PopsUpFragment fragment = new PopsUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_pops_up, container, false);
        EditText playername = (EditText)view.findViewById(R.id.playername);
        playername.setText(GameManager.sharedInfo.getString("mainPlayer","Player1"));
        TextView startplaying = (TextView) view.findViewById(R.id.Confirm);
        startplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(view.getContext(),MainGameScreen.class));
                getPlayer(view);
                GameManager.sharedInfoEditor.putInt("firstOpen",0).apply();

                getFragmentManager().beginTransaction()
                        .remove(PopsUpFragment.this).commit();
            }
        });

        TextView Cancel = (TextView) view.findViewById(R.id.Exit);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GameManager.getFirstOpen()==1){
                    getFragmentManager().beginTransaction()
                            .remove(PopsUpFragment.this).commit();
                    getActivity().finishAffinity();
                }
                else{
                    getFragmentManager().beginTransaction()
                            .remove(PopsUpFragment.this).commit();
                }
            }
        });

        LinearLayout lin = (LinearLayout) view.findViewById(R.id.linlayout);
        lin.setOnClickListener(this);
        addListener(view);

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onClick(View v) {

    }

    public void addListener(View view) {

        final CheckBox x = (CheckBox) view.findViewById(R.id.Xcheckbox);
        final CheckBox o = (CheckBox) view.findViewById(R.id.Ocheckbox);

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

        final EditText playername = (EditText)view.findViewById(R.id.playername);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
