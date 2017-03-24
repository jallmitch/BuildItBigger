package com.example.jessemitchell.builditbigger.free;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Jokes;
import com.example.jessemitchell.builditbigger.EndpointAsyncTask;
import com.example.jessemitchell.builditbigger.R;
import com.example.jokeactivitylib.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static com.example.jokeactivitylib.JokeActivity.HUMOR;


/**
 *  Had problems accessing the ListView inside of the RelativeLayout
 *  Fix: http://stackoverflow.com/questions/22112966/content-has-view-with-id-attribute-android-r-id-list-that-is-not-a-listview-c
 */
public class MainActivityFragment extends ListFragment {

    SharedPreferences prefs;

    private final  String[] values = new String[] {"Knock Knock","Question & Answer","Stories"};
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
        View root = inflater.inflate(R.layout.fragment_main_free, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.joketype, values);

        ListView listView = (ListView)root.findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            String humorType = "";
            switch(position)
            {
                case 1:
                    Toast.makeText(getContext(),"Please purchase the Paid version to access the Q&A jokes", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(),"Please purchase the Paid version to access the Story jokes", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    List<String> joke = new EndpointAsyncTask().execute(humorType, "0").get();

                    checkPref("knock");
                    Intent jalIntent = new Intent(getContext(), JokeActivity.class);
                    jalIntent.putExtra("JOKE", new ArrayList<>(joke));
                    startActivity(jalIntent);
                    break;
            }
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
