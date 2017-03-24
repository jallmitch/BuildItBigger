package com.example.jessemitchell.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.Jokes;
import com.example.jessemitchell.builditbigger.EndpointAsyncTask;
import com.example.jessemitchell.builditbigger.R;
import com.example.jokeactivitylib.JokeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends ListFragment {

    public static final String HUMOR = "humor";
    public static final String KNOCK = "knock";
    public static final String STORY = "story";
    public static final String QA = "qa";
    SharedPreferences prefs;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences(HUMOR, Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] values = new String[] {"Knock Knock","Question & Answer","Stories"};
        View root = inflater.inflate(R.layout.fragment_main_paid, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.joketype, values);

        ListView listView = (ListView)root.findViewById(R.id.humorListView);
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String jokeNumber;
        try {
            String humorType = "";
            switch(position)
            {
                case 1:
                    humorType = "qa";
                    jokeNumber = checkPref(QA);
                    break;
                case 2:
                    humorType = "story";
                    jokeNumber = checkPref(STORY);
                    break;
                default:
                    jokeNumber = checkPref(KNOCK);
                    break;

            }

            List<String> joke = new EndpointAsyncTask().execute(humorType, jokeNumber).get();

            ArrayList<String> contents = new ArrayList<>(joke);
            Intent jalIntent = new Intent(getContext(), JokeActivity.class);
            jalIntent.putStringArrayListExtra("JOKE", contents);
            startActivity(jalIntent);
        }
        catch(Exception e){

        }
    }

    private String checkPref(String jokeType)
    {
        Jokes jokes = new Jokes();
        SharedPreferences.Editor editPref = prefs.edit();

        editPref.putString("HumorType", jokeType);
        editPref.putInt("jokeSize", jokes.getSize(jokeType));
        editPref.putString(jokeType, "0");
        editPref.commit();

        return prefs.getString(jokeType, "0");
    }
}
