package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JokesAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<>(MainActivity.class);

    @Test
    public void doInBackground() throws Exception {
        JokesAsyncTask jokesAsyncTask = new JokesAsyncTask(mainActivityActivityTestRule.getActivity().getApplicationContext());
        jokesAsyncTask.execute();
        try {
            String result = jokesAsyncTask.get(20000, MILLISECONDS);
            assertNotNull(result);
            assertTrue(result.length() == 7);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}