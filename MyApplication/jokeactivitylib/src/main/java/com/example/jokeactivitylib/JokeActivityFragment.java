package com.example.jokeactivitylib;


import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeActivityFragment extends Fragment {

    private GridLayout mGridLayout;
    ArrayList<String> joke;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        boolean knockKnock = true;
        if (container != null)
            container = null;
        Bundle arguments = getArguments();
        if (arguments == null) {
            joke = getActivity().getIntent().getExtras().getStringArrayList("JOKE");
        }
        else{
            joke = arguments.getStringArrayList("JOKE");
        }
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        mGridLayout = (GridLayout)root.findViewById(R.id.jokesGridView);

        if (knockKnock)
        {
            buildKnockKnockView(joke.get(0), joke.get(1));
        }

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGridLayout.removeAllViews();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private TextView getTextView(String text, String color, int rowNumber)
    {
        GridLayout.LayoutParams textParam = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED,0),GridLayout.spec(GridLayout.UNDEFINED,0));
        textParam.rowSpec = GridLayout.spec(rowNumber);

        TextView openingState = new TextView(getActivity());

        if (color.equals("blue")) {
            textParam.columnSpec = GridLayout.spec(0,10,10f);
            textParam.rightMargin=1;
            openingState.setBackgroundResource(R.drawable.rounded_corner);
            openingState.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else {
            textParam.columnSpec = GridLayout.spec(2,10,10f);
            textParam.leftMargin=(1-20);
            openingState.setBackgroundResource(R.drawable.rounded_corner1);
        }

        openingState.setPadding(25,25,25,25);
        openingState.setText(text);
        openingState.setLayoutParams(textParam);

        return openingState;
    }

    private ImageView getDotsImage(String dotColor, int rowNumber)
    {
        ImageView blueDots = new ImageView(getActivity());
        GridLayout.LayoutParams blueDotParam = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED,0),GridLayout.spec(GridLayout.UNDEFINED,0));
        blueDotParam.height=90;
        blueDotParam.width=90;
        blueDotParam.rowSpec = GridLayout.spec(rowNumber);
        blueDots.setLayoutParams(blueDotParam);

        if (dotColor.equals("blue")) {
            blueDotParam.leftMargin=(1-20);
            blueDotParam.columnSpec = GridLayout.spec(10);
            blueDots.setBackgroundResource(R.drawable.dotsblue);
        }
        else {
            blueDotParam.rightMargin=1;
            blueDotParam.columnSpec = GridLayout.spec(1);
            blueDots.setBackgroundResource(R.drawable.dotsgreen);
        }
        return blueDots;
    }

    private ImageView getCharacter(String character, int rowNumber)
    {
        ImageView charView = new ImageView(getActivity());
        GridLayout.LayoutParams charParam = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED,0),GridLayout.spec(GridLayout.UNDEFINED,0));
        charParam.height=90;
        charParam.width=90;
        charParam.rowSpec = GridLayout.spec(rowNumber);
        charView.setLayoutParams(charParam);


        if (character.equals("donkey")) {
            charParam.columnSpec = GridLayout.spec(11);
            charView.setBackgroundResource(R.drawable.donkey);
            charParam.setGravity(Gravity.END);
        }
        else {
            charParam.columnSpec = GridLayout.spec(0);
            charView.setBackgroundResource(R.drawable.hyena);
        }

        return  charView;
    }

    private void buildKnockKnockView(String question, String punchLine)
    {
        mGridLayout.addView(getTextView(getString(R.string.knockopening),"blue", 1));
        mGridLayout.addView(getDotsImage("blue",1));
        mGridLayout.addView(getCharacter("donkey",1));

        mGridLayout.addView(getCharacter("hyena",2));
        mGridLayout.addView(getDotsImage("green",2));
        mGridLayout.addView(getTextView(getString(R.string.firstQuestion),"green",2));

        mGridLayout.addView(getTextView(question,"blue", 3));
        mGridLayout.addView(getDotsImage("blue",3));
        mGridLayout.addView(getCharacter("donkey",3));

        Resources res = getResources();
        String questionTwo = res.getString(R.string.secondQuestion, question);
        mGridLayout.addView(getCharacter("hyena",4));
        mGridLayout.addView(getDotsImage("green",4));
        mGridLayout.addView(getTextView(questionTwo,"green",4));

        mGridLayout.addView(getTextView(punchLine,"blue", 5));
        mGridLayout.addView(getDotsImage("blue",5));
        mGridLayout.addView(getCharacter("donkey",5));
    }

    private View buildQuestionAnswerView(View root,  ArrayList<String> joke)
    {
        return root;
    }

    private View buildStoryView(View root,  ArrayList<String> joke)
    {
        return root;
    }

}
