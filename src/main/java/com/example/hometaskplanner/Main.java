package com.example.hometaskplanner;

import com.example.hometaskplanner.model.RecurringTask;
import com.example.hometaskplanner.model.TaskEffort;
import com.example.hometaskplanner.model.TaskPriority;
import com.example.hometaskplanner.model.WeeklySchedule;
import com.example.hometaskplanner.service.PlannerService;
import com.example.hometaskplanner.service.TaskManager;

import java.util.Scanner;

/*
 * Main is the entry point of the Home Task & Routine Planner.
 *
 * This class is responsible for:
 * - displaying the menu
 * - reading user input
 * - calling the correct methods based on the user's menu choice
 * - connecting the user interface flow to the backend classes
 *
 * Main does not store task logic itself.
 * Instead:
 * - TaskManager manages recurring tasks
 * - PlannerService generates the weekly draft schedule
 */
public class Main {

    public static void main(String[] args) {
        // Scanner is used to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // TaskManager stores and manages the list of recurring tasks
        TaskManager taskManager = new TaskManager();

        // PlannerService contains the scheduling logic
        PlannerService plannerService = new PlannerService();

        // This boolean controls whether the program keeps running
        boolean running = true;

        // Main program loop:
        // keep showing the menu until the user chooses to exit
        while (running) {
            printMainMenu();
            int menuChoice = readMenuChoice(scanner);

            // Run the correct option based on the user's input
            switch (menuChoice) {
                case 1:
                    addRecurringTask(scanner, taskManager);
                    break;
                case 2:
                    viewAllTasks(taskManager);
                    break;
                case 3:
                    editTask(scanner, taskManager);
                    break;
                case 4:
                    deleteTask(scanner, taskManager);
                    break;
                case 5:
                    generateWeeklyDraftSchedule(taskManager, plannerService);
                    break;
                case 6:
                    // Placeholder for future functionality
                    System.out.println("Edit schedule suggestion option will be added next.");
                    break;
                case 7:
                    // Stop the loop and close the application
                    running = false;
                    System.out.println("Exiting Home Task & Routine Planner.");
                    break;
                default:
                    // Handles menu numbers outside the valid range
                    System.out.println("Invalid menu option.");
            }
        }

        // Close the scanner when the program finishes
        scanner.close();
    }

    /*
     * Displays the main menu options to the user.
     */
    private static void printMainMenu() {
        System.out.println("\n=== Home Task & Routine Planner ===");
        System.out.println("1. Add recurring task");
        System.out.println("2. View all tasks");
        System.out.println("3. Edit task");
        System.out.println("4. Delete task");
        System.out.println("5. Generate weekly draft schedule");
        System.out.println("6. Edit schedule suggestion");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    /*
     * Reads the user's menu choice.
     *
     * This method keeps asking until the user enters a valid whole number.
     */
    private static int readMenuChoice(Scanner scanner) {
        while (true) {
            String menuInput = scanner.nextLine().trim();

            try {
                return Integer.parseInt(menuInput);
            } catch (NumberFormatException exception) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    /*
     * Collects all input needed to create a new recurring task.
     *
     * The input is validated step by step before the task is created.
     * If valid, the task is added to TaskManager.
     */
    private static void addRecurringTask(Scanner scanner, TaskManager taskManager) {
        String taskName = readTaskName(scanner);
        TaskPriority taskPriority = readTaskPriority(scanner);
        TaskEffort taskEffort = readTaskEffort(scanner);
        String preferredDay = readPreferredDay(scanner);
        int preferredHour = readPreferredHour(scanner);

        try {
            RecurringTask recurringTask = new RecurringTask(
                    taskName,
                    taskPriority,
                    taskEffort,
                    preferredDay,
                    preferredHour
            );

            taskManager.addTask(recurringTask);
            System.out.println("Recurring task added successfully.");
        } catch (IllegalArgumentException exception) {
            // Handles any validation errors thrown by the RecurringTask class
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /*
     * Displays all tasks currently stored in TaskManager.
     *
     * If there are no tasks, the user is informed instead.
     */
    private static void viewAllTasks(TaskManager taskManager) {
        if (!taskManager.hasTasks()) {
            System.out.println("\nNo recurring tasks have been added yet.");
            return;
        }

        System.out.println("\n=== All Recurring Tasks ===");

        for (int taskIndex = 0; taskIndex < taskManager.getTaskCount(); taskIndex++) {
            System.out.println("\nTask " + (taskIndex + 1));
            System.out.println(taskManager.getTask(taskIndex));
        }
    }

    /*
     * Allows the user to edit an existing task.
     *
     * Steps:
     * 1. Show all current tasks
     * 2. Ask which task to edit
     * 3. Read updated values
     * 4. Update the selected task using setter methods
     */
    private static void editTask(Scanner scanner, TaskManager taskManager) {
        if (!taskManager.hasTasks()) {
            System.out.println("No recurring tasks available to edit.");
            return;
        }

        viewAllTasks(taskManager);
        System.out.print("Enter the task number to edit: ");
        int taskNumber = readMenuChoice(scanner) - 1;

        try {
            RecurringTask recurringTask = taskManager.getTask(taskNumber);

            String updatedTaskName = readTaskName(scanner);
            TaskPriority updatedTaskPriority = readTaskPriority(scanner);
            TaskEffort updatedTaskEffort = readTaskEffort(scanner);
            String updatedPreferredDay = readPreferredDay(scanner);
            int updatedPreferredHour = readPreferredHour(scanner);

            recurringTask.setTaskName(updatedTaskName);
            recurringTask.setTaskPriority(updatedTaskPriority);
            recurringTask.setTaskEffort(updatedTaskEffort);
            recurringTask.setPreferredDay(updatedPreferredDay);
            recurringTask.setPreferredHour(updatedPreferredHour);

            System.out.println("Task updated successfully.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /*
     * Allows the user to delete an existing task.
     *
     * Steps:
     * 1. Show all tasks
     * 2. Ask which task to delete
     * 3. Remove the selected task from TaskManager
     */
    private static void deleteTask(Scanner scanner, TaskManager taskManager) {
        if (!taskManager.hasTasks()) {
            System.out.println("No recurring tasks available to delete.");
            return;
        }

        viewAllTasks(taskManager);
        System.out.print("Enter the task number to delete: ");
        int taskNumber = readMenuChoice(scanner) - 1;

        try {
            taskManager.deleteTask(taskNumber);
            System.out.println("Task deleted successfully.");
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    /*
     * Generates and displays the weekly draft schedule.
     *
     * The schedule is created by PlannerService using all current tasks.
     */
    private static void generateWeeklyDraftSchedule(TaskManager taskManager, PlannerService plannerService) {
        if (!taskManager.hasTasks()) {
            System.out.println("No recurring tasks available to schedule.");
            return;
        }

        WeeklySchedule weeklySchedule = plannerService.generateDraftSchedule(taskManager.getAllTasks());
        System.out.println("\n=== Weekly Draft Schedule ===");
        System.out.println(weeklySchedule);
    }

    /*
     * Reads and validates the task name.
     *
     * Rules:
     * - cannot be empty
     * - must only contain letters and spaces
     *
     * The method keeps asking until valid input is entered.
     */
    private static String readTaskName(Scanner scanner) {
        while (true) {
            System.out.print("Enter task name: ");
            String taskName = scanner.nextLine().trim();

            if (taskName.isEmpty()) {
                System.out.println("Task name cannot be empty. Please try again.");
                continue;
            }

            if (!taskName.matches("[a-zA-Z ]+")) {
                System.out.println("Task name must only contain letters and spaces. Please try again.");
                continue;
            }

            return taskName;
        }
    }

    /*
     * Reads and validates the task priority.
     *
     * The input is converted to uppercase so values like "low" still work.
     */
    private static TaskPriority readTaskPriority(Scanner scanner) {
        while (true) {
            System.out.print("Enter task priority (LOW, MEDIUM, HIGH): ");
            String taskPriorityInput = scanner.nextLine().trim().toUpperCase();

            try {
                return TaskPriority.valueOf(taskPriorityInput);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid task priority. Please enter LOW, MEDIUM, or HIGH.");
            }
        }
    }

    /*
     * Reads and validates the task effort.
     *
     * The input is converted to uppercase so values like "medium" still work.
     */
    private static TaskEffort readTaskEffort(Scanner scanner) {
        while (true) {
            System.out.print("Enter task effort (LOW, MEDIUM, HIGH): ");
            String taskEffortInput = scanner.nextLine().trim().toUpperCase();

            try {
                return TaskEffort.valueOf(taskEffortInput);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid task effort. Please enter LOW, MEDIUM, or HIGH.");
            }
        }
    }

    /*
     * Reads the preferred day for the task.
     *
     * At this stage, this method only checks that the day is not empty.
     * You can make this stricter later by only allowing real weekdays.
     */
    private static String readPreferredDay(Scanner scanner) {
        while (true) {
            System.out.print("Enter preferred day: ");
            String preferredDay = scanner.nextLine();

            if (preferredDay != null && !preferredDay.trim().isEmpty()) {
                return preferredDay;
            }

            System.out.println("Preferred day cannot be empty. Please try again.");
        }
    }

    /*
     * Reads and validates the preferred hour.
     *
     * Rules:
     * - must be a whole number
     * - must be between 0 and 23
     */
    private static int readPreferredHour(Scanner scanner) {
        while (true) {
            System.out.print("Enter preferred hour (0 to 23): ");
            String preferredHourInput = scanner.nextLine().trim();

            try {
                int preferredHour = Integer.parseInt(preferredHourInput);

                if (preferredHour >= 0 && preferredHour <= 23) {
                    return preferredHour;
                }

                System.out.println("Preferred hour must be between 0 and 23.");
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}