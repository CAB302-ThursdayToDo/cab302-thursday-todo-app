package com.example.hometaskplanner.service;

import com.example.hometaskplanner.model.RecurringTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * TaskManager is responsible for storing and managing recurring tasks.
 *
 * This keeps task list logic out of Main.
 */
public class TaskManager {

    private ArrayList<RecurringTask> recurringTasks;

    public TaskManager() {
        this.recurringTasks = new ArrayList<>();
    }

    public void addTask(RecurringTask recurringTask) {
        if (recurringTask == null) {
            throw new IllegalArgumentException("Recurring task cannot be null.");
        }

        recurringTasks.add(recurringTask);
    }

    public List<RecurringTask> getAllTasks() {
        return Collections.unmodifiableList(recurringTasks);
    }

    public RecurringTask getTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= recurringTasks.size()) {
            throw new IllegalArgumentException("Invalid task index.");
        }

        return recurringTasks.get(taskIndex);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= recurringTasks.size()) {
            throw new IllegalArgumentException("Invalid task index.");
        }

        recurringTasks.remove(taskIndex);
    }

    public boolean hasTasks() {
        return !recurringTasks.isEmpty();
    }

    public int getTaskCount() {
        return recurringTasks.size();
    }
}