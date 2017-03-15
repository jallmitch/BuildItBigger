package com.example.jokeactivitylib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeActivityFragment extends Fragment {


    public JokeActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String joke = getActivity().getIntent().getStringExtra("Joke");
        View root = inflater.inflate(R.layout.fragment_joke, container, false);

        TextView jokeText = (TextView)root.findViewById(R.id.joke_text);
        jokeText.setText(joke);

        return root;
    }

}
