package taskmanager;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<TaskItem> taskItems;

    public TaskList() {
        this.taskItems = new ArrayList<>();
    }

    public void addTaskItem(TaskItem taskItem) {
        taskItems.add(taskItem);
    }

    public void editTaskItem(TaskItem taskItem, int index) {
        taskItems.set(index, taskItem);
    }

    public void changeTaskItemComplitedStatus(int index) {
        TaskItem tmpTaskItem = taskItems.get(index);

        if (tmpTaskItem.isCompleted()) {
            tmpTaskItem.setUncompleted();
        }
        else {
            tmpTaskItem.setCompleted();
        }

        taskItems.set(index, tmpTaskItem);
    }

    public TaskItem getTaskItem(int index) {
        return taskItems.get(index);
    }

    public void removeTaskItem(int index) {
        taskItems.remove(index);
    }

    public int getTaskItemsCount() {
        return taskItems.size();
    }
}
