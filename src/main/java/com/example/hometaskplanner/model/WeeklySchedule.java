package com.example.hometaskplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * WeeklySchedule stores all schedule suggestions for the week.
 *
 * This class acts as a container for the generated schedule.
 * It keeps the list private and provides controlled access to it.
 */
public class WeeklySchedule {

    // Stores all schedule suggestions for the week
    private ArrayList<ScheduleSuggestion> scheduleSuggestions;

    /*
     * Constructor creates an empty weekly schedule.
     */
    public WeeklySchedule() {
        this.scheduleSuggestions = new ArrayList<>();
    }

    /*
     * Adds a new suggestion to the weekly schedule.
     */
    public void addSuggestion(ScheduleSuggestion scheduleSuggestion) {
        if (scheduleSuggestion == null) {
            throw new IllegalArgumentException("Schedule suggestion cannot be null.");
        }

        scheduleSuggestions.add(scheduleSuggestion);
    }

    /*
     * Returns a read-only view of the suggestions.
     *
     * This protects the internal collection from being modified directly outside the class.
     */
    public List<ScheduleSuggestion> getScheduleSuggestions() {
        return Collections.unmodifiableList(scheduleSuggestions);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        String[] daysOfWeek = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };

        for (String day : daysOfWeek) {
            output.append(day).append("\n");

            boolean taskFoundForDay = false;

            for (ScheduleSuggestion scheduleSuggestion : scheduleSuggestions) {
                if (scheduleSuggestion.getSuggestedDay().equalsIgnoreCase(day)) {
                    output.append("- ").append(scheduleSuggestion).append("\n");
                    taskFoundForDay = true;
                }
            }

            if (!taskFoundForDay) {
                output.append("- No tasks scheduled\n");
            }

            output.append("\n");
        }

        return output.toString();
    }
}