package com.example.jokelibrary;

import java.util.Random;

public class Jokes {

    private final String jokes[] = {
            "Joke #1",
            "Joke #2",
            "Joke #3",
            "Joke #4"
    };

    public String getJokes() {
        Random random = new Random();
        int randomIndex = random.nextInt(jokes.length - 1);

        return jokes[randomIndex];
    }
}
