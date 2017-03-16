package com.example.jessemitchell.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by jesse.mitchell on 3/16/2017.
 */
@RunWith(AndroidJUnit4.class)
public class JokeAsyncTest {

    @Test
    public void testAsynch() throws Exception
    {

        EndpointAsyncTask task = new EndpointAsyncTask();
        String joke = task.execute("").get();
        assertNotEquals("AsyncTask did not provide a returnable string.","",joke);
    }
}
