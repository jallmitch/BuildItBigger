/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jesse.mitchell.builditbigger.jokesapi;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokesapi.builditbigger.mitchell.jesse.example.com",
                ownerName = "jokesapi.builditbigger.mitchell.jesse.example.com"
        )
)
public class MyEndpoint {

    private final Jokes jokes = new Jokes();
    private final MyBean response = new MyBean();
    /**
     * A simple endpoint method that takes a name and says Hi back
     */

    @ApiMethod(name = "knockKnock")
    public MyBean knockKnock(@Named("kk") int jokeNumber)
    {
        response.setData(jokes.getKnockKnock(jokeNumber));
        return response;
    }

    @ApiMethod(name = "questionAnswer")
    public MyBean questionAnswer(@Named("qa") int jokeNumber)
    {
        response.setData(jokes.getQA(jokeNumber));
        return response;
    }

    @ApiMethod(name = "story")
    public MyBean story(@Named("story") int jokeNumber)
    {
        response.setData(jokes.getStory(jokeNumber));
        return response;
    }

}
