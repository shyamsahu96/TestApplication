package com.example.testapplication.rxjava;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Task> createTasksList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Take out the Trash...", true, 3));
        tasks.add(new Task("Take out the Dog...", false, 1));
        tasks.add(new Task("Take out your girlfriend...", true, 0));
        tasks.add(new Task("Take out the Anger in your mind...", false, 8));
        tasks.add(new Task("Take out your enemies...", true, 2));
        return tasks;
    }
}
