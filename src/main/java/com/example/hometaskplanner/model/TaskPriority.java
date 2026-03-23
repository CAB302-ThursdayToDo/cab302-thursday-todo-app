package com.example.hometaskplanner.model;

/*
 * TaskPriority represents how important a recurring task is.
 *
 * We use an enum because priority should only be one of a fixed set of values.
 * This is safer than storing priority as a String because it prevents invalid data.
 */
public enum TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}