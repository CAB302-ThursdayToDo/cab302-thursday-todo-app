package com.example.hometaskplanner.model;

/*
 * ScheduleSuggestion represents one suggested placement of a task
 * in the weekly routine.
 *
 * It also stores the draft schedule suggestion generated for the user.
 *
 * It also includes a confidence label so the app can highlight
 * low-confidence suggestions instead of pretending everything is certain.
 */
public class ScheduleSuggestion {

    // The recurring task being suggested
    private RecurringTask recurringTask;

    // The scheduled day suggested for this task
    private String suggestedDay;

    // The scheduled hour suggested for this task
    private int suggestedHour;

    // A simple confidence label such as "HIGH", "MEDIUM", or "LOW"
    private String confidenceLevel;

    /*
     * Constructor for a schedule suggestion.
     */
    public ScheduleSuggestion(RecurringTask recurringTask, String suggestedDay,
                              int suggestedHour, String confidenceLevel) {
        setRecurringTask(recurringTask);
        setSuggestedDay(suggestedDay);
        setSuggestedHour(suggestedHour);
        setConfidenceLevel(confidenceLevel);
    }

    public RecurringTask getRecurringTask() {
        return recurringTask;
    }

    public void setRecurringTask(RecurringTask recurringTask) {
        if (recurringTask == null) {
            throw new IllegalArgumentException("Recurring task cannot be null.");
        }

        this.recurringTask = recurringTask;
    }

    public String getSuggestedDay() {
        return suggestedDay;
    }

    public void setSuggestedDay(String suggestedDay) {
        if (suggestedDay == null || suggestedDay.trim().isEmpty()) {
            throw new IllegalArgumentException("Suggested day cannot be empty.");
        }

        this.suggestedDay = suggestedDay.trim();
    }

    public int getSuggestedHour() {
        return suggestedHour;
    }

    public void setSuggestedHour(int suggestedHour) {
        if (suggestedHour < 0 || suggestedHour > 23) {
            throw new IllegalArgumentException("Suggested hour must be between 0 and 23.");
        }

        this.suggestedHour = suggestedHour;
    }

    public String getConfidenceLevel() {
        return confidenceLevel;
    }

    /*
     * For Week 5, confidence is kept simple as a String.
     * Later, we could convert this into an enum as well.
     */
    public void setConfidenceLevel(String confidenceLevel) {
        if (confidenceLevel == null || confidenceLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Confidence level cannot be empty.");
        }

        this.confidenceLevel = confidenceLevel.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return recurringTask.getTaskName() + " at " +
                String.format("%02d:00", suggestedHour) +
                " (" + formatText(confidenceLevel) + " confidence)";
    }

    private String formatText(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}