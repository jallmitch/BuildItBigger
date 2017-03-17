package com.example.jessemitchell.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jessemitchell.builditbigger.EndpointAsyncTask;
import com.example.jessemitchell.builditbigger.R;
import com.example.jokeactivitylib.JokeActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityFragment extends ListFragment {

    public MainActivityFragment() {
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
        try {
            String humorType = "";
            switch(position)
            {
                case 1: humorType = "qa"; break;
                case 2: humorType = "story"; break;
            }
            List<String> joke = new EndpointAsyncTask().execute(humorType, "0").get();
            Intent jalIntent = new Intent(getContext(), JokeActivity.class);
            jalIntent.putExtra("Joke", joke.get(1));
            startActivity(jalIntent);
        }
        catch(Exception e){

        }
    }
}
