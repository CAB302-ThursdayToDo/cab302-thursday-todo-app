package com.example.hometaskplanner.service;

import com.example.hometaskplanner.model.RecurringTask;
import com.example.hometaskplanner.model.ScheduleSuggestion;
import com.example.hometaskplanner.model.TaskEffort;
import com.example.hometaskplanner.model.TaskPriority;
import com.example.hometaskplanner.model.WeeklySchedule;

import java.util.List;

/*
 * PlannerService contains the backend logic for building
 * a draft weekly schedule from a list of recurring tasks.
 *
 * This is a service class because it performs business logic.
 * It does not just store data.
 */
public class PlannerService {

    /*
     * Generates a draft weekly schedule from recurring tasks.
     *
     * For Week 5, we keep the rules simple:
     * - Use the preferred day and preferred hour directly
     * - Generate a confidence level based on priority and effort
     *
     * This gives the team a working backend foundation that
     * can later be improved or connected to AI-generated logic.
     */
    public WeeklySchedule generateDraftSchedule(List<RecurringTask> recurringTasks) {
        WeeklySchedule weeklySchedule = new WeeklySchedule();

        if (recurringTasks == null || recurringTasks.isEmpty()) {
            return weeklySchedule;
        }

        for (RecurringTask recurringTask : recurringTasks) {
            String confidenceLevel = determineConfidenceLevel(recurringTask);

            ScheduleSuggestion scheduleSuggestion = new ScheduleSuggestion(
                    recurringTask,
                    recurringTask.getPreferredDay(),
                    recurringTask.getPreferredHour(),
                    confidenceLevel
            );

            weeklySchedule.addSuggestion(scheduleSuggestion);
        }

        return weeklySchedule;
    }

    /*
     * Determines a simple confidence level for the suggestion.
     *
     * Example simple rules:
     * - HIGH priority and LOW effort = HIGH confidence
     * - LOW priority and HIGH effort = LOW confidence
     * - otherwise = MEDIUM confidence
     *
     * These rules are simple enough for Week 5 and easy to explain.
     */
    private String determineConfidenceLevel(RecurringTask recurringTask) {
        if (recurringTask.getTaskPriority() == TaskPriority.HIGH
                && recurringTask.getTaskEffort() == TaskEffort.LOW) {
            return "HIGH";
        }

        if (recurringTask.getTaskPriority() == TaskPriority.LOW
                && recurringTask.getTaskEffort() == TaskEffort.HIGH) {
            return "LOW";
        }

        return "MEDIUM";
    }
}