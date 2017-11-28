package com.abderraouf.gattal.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class WinOrLoseFragment extends Fragment {

    private View mView;
    private OnFragmentInteractionListener mListener;

    public WinOrLoseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_win_or_lose, container, false);
        ImageView close = mView.findViewById(R.id.Close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MainMenu.class));
            }
        });

        ImageView playAgain = mView.findViewById(R.id.PlayAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .remove(WinOrLoseFragment.this).commit();
            }
        });

        ImageView img = mView.findViewById(R.id.WinLoseBoard);
        int res = Detector.getResult();
        if(res==2){
            img.setImageResource(R.drawable.nullgame);
        }
        else if(res == MainGameScreen.xoro){
            img.setImageResource(R.drawable.youwin);
        }
        else{
            img.setImageResource(R.drawable.youlose);
        }
        return mView;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentCommunicator) {
            communicator = (FragmentCommunicator) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if (activity instanceof FragmentCommunicator) {
            Detector = (FragmentResultDetector) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        communicator.fragmentDetached();
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);

    }

    FragmentCommunicator communicator;
    public interface FragmentCommunicator {
        public void fragmentDetached();
    }

    FragmentResultDetector Detector;
    public interface FragmentResultDetector {
        public int getResult();
    }

}
