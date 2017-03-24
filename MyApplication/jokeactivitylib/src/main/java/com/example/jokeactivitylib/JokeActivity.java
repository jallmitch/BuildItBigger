package com.example.jokeactivitylib;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Jokes;

import java.util.ArrayList;

public class JokeActivity extends FragmentActivity {

    public static final String HUMOR = "humor";
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_main);
        prefs = getSharedPreferences(HUMOR, Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getPrevious(View view)
    {
        getJokes(false);
    }

    public void getNext(View view)
    {
        getJokes(true);
    }

    private void getJokes(boolean isNext)
    {
        Jokes jokes = new Jokes();
        String humorType = prefs.getString("HumorType", "knock");
        String jokeNumber = prefs.getString(humorType, "0");

        int nextJoke;
        if (isNext)
            nextJoke = Integer.parseInt(jokeNumber)+1;
        else
            nextJoke = Integer.parseInt(jokeNumber)-1;

        SharedPreferences.Editor editJokeNum = prefs.edit();
        editJokeNum.putString(humorType,  String.valueOf(nextJoke));
        editJokeNum.commit();

        ArrayList<String> newJoke;
        switch(humorType){
            case "qa":
                newJoke = new ArrayList<>(jokes.getQA(nextJoke));
                break;
            case "story":
                newJoke = new ArrayList<>(jokes.getStory(nextJoke));
                break;
            default:
                newJoke = new ArrayList<>(jokes.getKnockKnock(nextJoke));
                break;
        }

        Bundle jokeBundle = new Bundle();
        jokeBundle.putStringArrayList("JOKE", newJoke);

        JokeActivityFragment frag = new JokeActivityFragment();
        frag.setArguments(jokeBundle);

        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(frag,null);
        ft.replace(R.id.fragment, frag);
        ft.commit();
    }
}
