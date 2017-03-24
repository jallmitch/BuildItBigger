package com.example.jokeactivitylib;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jokeactivitylib.JokeActivity.HUMOR;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeActivityFragment extends Fragment {

    private SharedPreferences prefs;
    private ArrayList<String> joke;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null)
            container.removeAllViews();

        prefs = this.getActivity().getSharedPreferences(HUMOR, Context.MODE_PRIVATE);

        String humorType = prefs.getString(getString(R.string.humor_type), getString(R.string.knock));
        String jokeExtra = getString(R.string.extra_data);
        Bundle arguments = getArguments();
        if (arguments == null) {
            joke = getActivity().getIntent().getExtras().getStringArrayList(jokeExtra);
        }
        else{
            joke = arguments.getStringArrayList(jokeExtra);
        }

        boolean isFirst = true;
        boolean isLast = false;

        int jokeSize = prefs.getInt(getString(R.string.joke_size),0);
        int jokeNum = Integer.parseInt(joke.get(0));

        if (jokeNum == 1) {
            isFirst = true;
            isLast = false;
        }
        else if (jokeNum < jokeSize) {
            isFirst = false;
            isLast = false;
        }
        else if (jokeNum == jokeSize){
            isFirst = false;
            isLast = true;
        }

        switch (humorType)
        {
            case "qa":
                View qa_root = inflater.inflate(R.layout.qa_joke_frag, container, false);

                Button qa_previous = (Button)qa_root.findViewById(R.id.qa_previous);
                if (isFirst){
                    qa_previous.setVisibility(View.INVISIBLE);
                }
                else{
                    qa_previous.setVisibility(View.VISIBLE);
                }

                Button qa_next = (Button)qa_root.findViewById(R.id.qa_next);
                if (isLast){
                    qa_next.setVisibility(View.INVISIBLE);
                }
                else{
                    qa_next.setVisibility(View.VISIBLE);
                }

                TextView question = (TextView)qa_root.findViewById(R.id.qa_Question);
                question.setText(joke.get(2));

                TextView answer = (TextView)qa_root.findViewById(R.id.qa_Answer);
                answer.setText(joke.get(3));
                return qa_root;
            case "story":
                View story_root = inflater.inflate(R.layout.story_joke_frag, container, false);

                Button story_previous = (Button)story_root.findViewById(R.id.story_previous);
                if (isFirst){
                    story_previous.setVisibility(View.INVISIBLE);
                }
                else{
                    story_previous.setVisibility(View.VISIBLE);
                }

                Button story_next = (Button)story_root.findViewById(R.id.story_next);
                if (isLast){
                    story_next.setVisibility(View.INVISIBLE);
                }
                else{
                    story_next.setVisibility(View.VISIBLE);
                }

                TextView title = (TextView)story_root.findViewById(R.id.storyTitle);
                title.setText(joke.get(2));

                TextView content = (TextView)story_root.findViewById(R.id.storyContent);
                content.setText(joke.get(3));
                return story_root;
            default:
                View root = inflater.inflate(R.layout.knock_joke_frag, container, false);

                Button previous = (Button)root.findViewById(R.id.previous);
                if (isFirst){
                    previous.setVisibility(View.INVISIBLE);
                }
                else{
                    previous.setVisibility(View.VISIBLE);
                }

                Button next = (Button)root.findViewById(R.id.next);
                if (isLast){
                    next.setVisibility(View.INVISIBLE);
                }
                else{
                    next.setVisibility(View.VISIBLE);
                }

                TextView subject = (TextView)root.findViewById(R.id.knockSubject);
                subject.setText(joke.get(2));

                Resources res = getResources();
                String questionTwo = res.getString(R.string.secondResponse, joke.get(2));
                TextView subjectResponse = (TextView)root.findViewById(R.id.subjectResponse);
                subjectResponse.setText(questionTwo);

                TextView punchLine = (TextView)root.findViewById(R.id.knockPunchLine);
                punchLine.setText(joke.get(3));
                return root;
        }
    }

}
