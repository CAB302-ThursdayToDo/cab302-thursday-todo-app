package com.example.hometaskplanner.model;

/*
 * RecurringTask represents one home task that appears regularly.
 *
 * Examples:
 * - Clean kitchen
 * - Vacuum living room
 * - Do laundry
 *
 * This class is part of the model because it stores the task data.
 */
public class RecurringTask {

    // The title of the task, e.g. "Clean Kitchen"
    private String taskName;

    // The task priority level
    private TaskPriority taskPriority;

    // The effort required for the task
    private TaskEffort taskEffort;

    // The preferred day for the task, e.g. "Monday"
    private String preferredDay;

    // The preferred hour for the task, using 24-hour time from 0 to 23
    private int preferredHour;

    /*
     * Constructor for creating a recurring task.
     */
    public RecurringTask(String taskName, TaskPriority taskPriority, TaskEffort taskEffort,
                         String preferredDay, int preferredHour) {
        setTaskName(taskName);
        setTaskPriority(taskPriority);
        setTaskEffort(taskEffort);
        setPreferredDay(preferredDay);
        setPreferredHour(preferredHour);
    }

    public String getTaskName() {
        return taskName;
    }

    /*
     * Task name validation:
     * - cannot be null
     * - cannot be blank
     * - must only contain letters and spaces
     */
    public void setTaskName(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name cannot be empty.");
        }

        if (!taskName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Task name must only contain letters and spaces.");
        }

        this.taskName = taskName.trim();
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        if (taskPriority == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }

        this.taskPriority = taskPriority;
    }

    public TaskEffort getTaskEffort() {
        return taskEffort;
    }

    public void setTaskEffort(TaskEffort taskEffort) {
        if (taskEffort == null) {
            throw new IllegalArgumentException("Task effort cannot be null.");
        }

        this.taskEffort = taskEffort;
    }

    public String getPreferredDay() {
        return preferredDay;
    }

    /*
     * Preferred day validation:
     * - cannot be null
     * - cannot be blank
     */
    public void setPreferredDay(String preferredDay) {
        if (preferredDay == null || preferredDay.trim().isEmpty()) {
            throw new IllegalArgumentException("Preferred day cannot be empty.");
        }

        this.preferredDay = preferredDay.trim();
    }

    public int getPreferredHour() {
        return preferredHour;
    }

    /*
     * Preferred hour must be between 0 and 23.
     */
    public void setPreferredHour(int preferredHour) {
        if (preferredHour < 0 || preferredHour > 23) {
            throw new IllegalArgumentException("Preferred hour must be between 0 and 23.");
        }

        this.preferredHour = preferredHour;
    }

    @Override
    public String toString() {
        return "Name: " + taskName + "\n" +
                "Priority: " + formatText(taskPriority.name()) + "\n" +
                "Effort: " + formatText(taskEffort.name()) + "\n" +
                "Preferred Day: " + formatDay(preferredDay) + "\n" +
                "Preferred Time: " + formatHour(preferredHour);
    }

    /*
     * Converts enum text like HIGH into High.
     */
    private String formatText(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    /*
     * Converts day text like monday into Monday.
     */
    private String formatDay(String day) {
        return day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase();
    }

    /*
     * Converts an integer hour like 6 into 06:00.
     */
    private String formatHour(int hour) {
        return String.format("%02d:00", hour);
    }
}