package com.example.jokelibrary;

import java.util.Random;

public class Jokes {

    private final String jokes[] = {
            "Some Joke",
            "Another Joke",
            "Joke #3",
            "Joke #D"
    };

    public String getJokes() {
        Random random = new Random();
        int randomIndex = random.nextInt(jokes.length - 1);

        return jokes[randomIndex];
    }
}
