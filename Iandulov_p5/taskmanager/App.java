package taskmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private TaskList tasks;
    private Scanner input;

    public App() {
        this.input = new Scanner(System.in);
    }

    public void start() {
        mainMenu();

        this.input.close();
    }

    private void mainMenu() {
        boolean isOpen = true;
        int menuItem;

        while (isOpen) {
            showMainMenu();
            menuItem = getSelectedItemIndex(1, 3);
            switch (menuItem) {
                case 1:
                    createNewTaskList();
                    listOperationMenu();
                    break;
                case 2:
                    loadTaskListFromFile();
                    listOperationMenu();
                    break;
                case 3:
                    isOpen = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void createNewTaskList() {
        tasks = new TaskList();

        System.out.println("New task list has been created");
    }

    private void loadTaskListFromFile() {
        tasks = new TaskList();

        boolean isErrorInFile = false;
        String fileName;

        System.out.print("Enter the filename to load: ");
        fileName = input.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            TaskItem taskItem;
            boolean isCompleted;
            String taskTitle, taskDescription, tmpDueDate;
            Date dueDate = null;
            while (fileScanner.hasNextLine()) {
                taskTitle = fileScanner.nextLine();
                taskDescription = fileScanner.nextLine();
                tmpDueDate = fileScanner.nextLine();
                try {
                    dueDate = dateFormat.parse(tmpDueDate);
                }
                catch (ParseException e) {
                    System.out.print("Error (wrong date format), try again: ");
                }
                String tmpIsCompleted = fileScanner.nextLine();
                isCompleted = !tmpIsCompleted.equals("0");

                try {
                    taskItem = new TaskItem(taskTitle, taskDescription, dueDate);
                    if (isCompleted) {
                        taskItem.setCompleted();
                    } else {
                        taskItem.setUncompleted();
                    }
                    tasks.addTaskItem(taskItem);
                }
                catch (EmptyTaskItemTitle e) {
                    isErrorInFile = true;
                    break;
                }
            }
        }
        catch (Exception e) {
            isErrorInFile = true;
        }

        if (isErrorInFile) {
            tasks = new TaskList();
            System.out.println("An error occurred, task list has not been loaded");
            System.out.println("Аn empty tasks list was created");
            System.out.println();
        }
        else {
            System.out.println("Task list has been loaded");
            System.out.println();
        }
    }

    private void listOperationMenu() {
        boolean isOpen = true;
        int menuItem;

        while (isOpen) {
            showListOperationMenu();
            menuItem = getSelectedItemIndex(1, 8);
            switch (menuItem) {
                case 1:
                    showTaskList(TaskListShowMode.ALL);
                    break;
                case 2:
                    addTaskItem();
                    break;
                case 3:
                    showTaskList(TaskListShowMode.ALL);
                    editTaskItem();
                    break;
                case 4:
                    showTaskList(TaskListShowMode.ALL);
                    removeTaskItem();
                    break;
                case 5:
                    showTaskList(TaskListShowMode.UNCOMPLETED);
                    markTaskItemCompleted();
                    break;
                case 6:
                    showTaskList(TaskListShowMode.COMPLETED);
                    markTaskItemUncompleted();
                    break;
                case 7:
                    saveTaskListToFile();
                    break;
                case 8:
                    isOpen = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void showTaskList(TaskListShowMode showMode) {
        switch (showMode) {
            case ALL:
                System.out.println("Current Tasks");
                System.out.println("-------------");
                break;
            case COMPLETED:
                System.out.println("Completed Tasks");
                System.out.println("---------------");
                break;
            case UNCOMPLETED:
                System.out.println("Uncompleted Tasks");
                System.out.println("-----------------");
                break;
        }
        System.out.println();

        boolean isNoTasks = true;
        int n = tasks.getTaskItemsCount();
        TaskItem taskItem;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0, j = 1; i < n; i++) {
            taskItem = tasks.getTaskItem(i);
            if ((showMode == TaskListShowMode.ALL) &&
                taskItem.isCompleted()) {
                isNoTasks = false;
                System.out.printf("%d) ***[%s] %s: %s%n",
                        j++,
                        dateFormat.format(taskItem.getDueDate()),
                        taskItem.getTitle(),
                        taskItem.getDescription());
            }
            else if (((showMode == TaskListShowMode.ALL) &&
                       !taskItem.isCompleted()) ||
                     ((showMode == TaskListShowMode.COMPLETED) &&
                       taskItem.isCompleted()) ||
                     ((showMode == TaskListShowMode.UNCOMPLETED) &&
                       !taskItem.isCompleted())){
                isNoTasks = false;
                System.out.printf("%d) [%s] %s: %s%n",
                        j++,
                        dateFormat.format(taskItem.getDueDate()),
                        taskItem.getTitle(),
                        taskItem.getDescription());
            }
        }

        if (isNoTasks)
        {
            System.out.println("\tempty");
        }
        System.out.println();
    }

    private void addTaskItem() {
        tasks.addTaskItem(buildTaskItem(false));
    }

    private void editTaskItem() {
        int n = tasks.getTaskItemsCount();

        if (n > 0) {
            System.out.println("Which task will you edit?");

            int menuItem = getSelectedItemIndex(1, n);

            tasks.editTaskItem(buildTaskItem(true), menuItem - 1);
        }
        else {
            System.out.print("List of tasks is empty, press enter to continue...");
            input.nextLine();
            System.out.println();
        }
    }

    private TaskItem buildTaskItem(boolean isEditing) {
        String taskTitle, taskDescription, tmpDueDate;
        Date dueDate;

        if (!isEditing) {
            System.out.print("Task title: ");
        }
        else {
            System.out.print("New task title: ");
        }
        do {
            taskTitle = input.nextLine();

            if (taskTitle.length() == 0) {
                System.out.print("Error (empty title), try again: ");
            }
        } while (taskTitle.length() == 0);


        if (!isEditing) {
            System.out.print("Task description: ");
        }
        else {
            System.out.print("New task description: ");
        }
        taskDescription = input.nextLine();

        if (!isEditing) {
            System.out.print("Task due date (YYYY-MM-DD): ");
        }
        else {
            System.out.print("New task due date (YYYY-MM-DD): ");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        do {
            tmpDueDate = input.nextLine();

            try {
                dueDate = dateFormat.parse(tmpDueDate);
            }
            catch (ParseException e) {
                System.out.print("Error (wrong date format), try again: ");
                continue;
            }

            break;
        } while (true);

        try {
            return new TaskItem(taskTitle, taskDescription, dueDate);
        }
        catch (EmptyTaskItemTitle e) {
            return null;
        }
    }

    private void removeTaskItem() {
        int n = tasks.getTaskItemsCount();

        if (n > 0) {
            System.out.println("Which task will you remove?");

            int menuItem = getSelectedItemIndex(1, n);

            tasks.removeTaskItem(menuItem - 1);
        }
        else {
            System.out.print("List of tasks is empty, press enter to continue...");
            input.nextLine();
            System.out.println();
        }
    }

    private void markTaskItemCompleted() {
        int n = tasks.getTaskItemsCount(), m = 0;

        TaskItem taskItem;
        for (int i = 0; i < n; i++) {
            taskItem = tasks.getTaskItem(i);
            if (!taskItem.isCompleted()) {
                m++;
            }
        }

        if (m > 0) {
            System.out.println("Which task will you mark as completed?");

            int menuItem = getSelectedItemIndex(1, m);

            for (int i = 0, j = 0; i < n; i++) {
                taskItem = tasks.getTaskItem(i);
                if (!taskItem.isCompleted()) {
                    j++;
                    if (j == menuItem) {
                        taskItem.setCompleted();
                        break;
                    }
                }
            }
        }
        else {
            System.out.print("List of uncompleted tasks is empty, press enter to continue...");
            input.nextLine();
            System.out.println();
        }
    }

    private void markTaskItemUncompleted() {
        int n = tasks.getTaskItemsCount(), m = 0;

        TaskItem taskItem;
        for (int i = 0; i < n; i++) {
            taskItem = tasks.getTaskItem(i);
            if (taskItem.isCompleted()) {
                m++;
            }
        }

        if (m > 0) {
            System.out.println("Which task will you unmark as completed?");

            int menuItem = getSelectedItemIndex(1, m);

            for (int i = 0, j = 0; i < n; i++) {
                taskItem = tasks.getTaskItem(i);
                if (taskItem.isCompleted()) {
                    j++;
                    if (j == menuItem) {
                        taskItem.setUncompleted();
                        break;
                    }
                }
            }
        }
        else {
            System.out.print("List of completed tasks is empty, press enter to continue...");
            input.nextLine();
            System.out.println();
        }
    }

    private void saveTaskListToFile() {
        String fileName;

        System.out.print("Enter the filename to save as: ");
        fileName = input.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            FileWriter writer = new FileWriter(fileName);

            int n = tasks.getTaskItemsCount();

            TaskItem taskItem;
            for (int i = 0; i < n; i++) {
                taskItem = tasks.getTaskItem(i);
                writer.write(taskItem.getTitle() + '\n');
                writer.write(taskItem.getDescription() + '\n');
                writer.write(dateFormat.format(taskItem.getDueDate()) + '\n');
                writer.write(taskItem.isCompleted() ? "1\n" : "0\n");
            }
            writer.close();
            System.out.println("Task list has been saved");
            System.out.println();
        }
        catch (IOException e) {
            System.out.println("An error occurred, task list has not been saved");
        }
    }

    private int getSelectedItemIndex(int minValue, int maxValue) {
        boolean isInputError;
        int selection = minValue - 1;

        do {
            System.out.print("> ");

            isInputError = false;
            try {
                selection = input.nextInt();
            }
            catch (InputMismatchException e) {
                isInputError = true;
            }
            input.nextLine();

            if ((selection < minValue) || (selection > maxValue) || isInputError) {
                System.out.println("Error, try again");
            }
            else {
                break;
            }
        } while (true);

        System.out.println();

        return selection;
    }

    private void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println();
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
    }

    private void showListOperationMenu() {
        System.out.println("List Operation Menu");
        System.out.println("-------------------");
        System.out.println();
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");
        System.out.println();
    }
}
