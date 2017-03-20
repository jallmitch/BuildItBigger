package com.example.jokeactivitylib;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Jokes;

import java.util.ArrayList;

public class JokeActivity extends AppCompatActivity {

    public static final String HUMOR = "humor";
    public static final String KNOCK = "knock";
    public static final String STORY = "story";
    public static final String QA = "questAnswer";
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
        Jokes jokes = new Jokes();
        String jokeNumber = prefs.getString(KNOCK, "0");
        int prevJoke = Integer.parseInt(jokeNumber)-1;
        jokes.getKnockKnock(prevJoke);

        SharedPreferences.Editor editJokeNum = prefs.edit();
        editJokeNum.putInt(KNOCK, prevJoke);
        editJokeNum.commit();

        Bundle jokeBundle = new Bundle();
        jokeBundle.putStringArrayList("JOKE", new ArrayList<>(jokes.getKnockKnock(prevJoke)));

        JokeActivityFragment frag = new JokeActivityFragment();
        frag.setArguments(jokeBundle);

        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, frag);
        ft.commit();
    }

    public void getNext(View view)
    {
        Jokes jokes = new Jokes();
        String jokeNumber = prefs.getString(KNOCK, "0");
        int nextJoke = Integer.parseInt(jokeNumber)+1;

        SharedPreferences.Editor editJokeNum = prefs.edit();
        editJokeNum.putString(KNOCK, String.valueOf(nextJoke));
        editJokeNum.commit();

        Bundle jokeBundle = new Bundle();
        jokeBundle.putStringArrayList("JOKE", new ArrayList<>(jokes.getKnockKnock(nextJoke)));

        JokeActivityFragment frag = new JokeActivityFragment();
        frag.setArguments(jokeBundle);

        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, frag);
        ft.commit();
    }
}
