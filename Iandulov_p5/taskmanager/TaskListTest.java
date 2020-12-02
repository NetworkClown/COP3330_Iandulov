package taskmanager;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private TaskList taskList;

    @Test
    void addingTaskItemsIncreasesSize() {
        taskList = new TaskList();
        int n1 = taskList.getTaskItemsCount();

        try {
            taskList.addTaskItem(new TaskItem("some title", "some description",
                                              new Date(System.currentTimeMillis())));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        int n2 = taskList.getTaskItemsCount();
        assertTrue(n1 < n2);
    }

    @Test
    void removingTaskItemsDecreasesSize() {
        taskList = new TaskList();
        TaskItem taskItem = null;

        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        taskList.addTaskItem(taskItem);
        taskList.addTaskItem(taskItem);
        taskList.addTaskItem(taskItem);
        int n1 = taskList.getTaskItemsCount();
        taskList.removeTaskItem(0);
        int n2 = taskList.getTaskItemsCount();
        assertTrue(n1 > n2);
    }

    @Test
    void newTaskListIsEmpty() {
        taskList = new TaskList();

        assertEquals(0, taskList.getTaskItemsCount());
    }

    @Test
    void checkingMethodGetTaskItemsCount() {
        taskList = new TaskList();
        TaskItem taskItem = null;

        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        int n = taskList.getTaskItemsCount();
        assertEquals(0, n);

        taskList.addTaskItem(taskItem);
        n = taskList.getTaskItemsCount();
        assertEquals(1, n);

        taskList.addTaskItem(taskItem);
        n = taskList.getTaskItemsCount();
        assertEquals(2, n);

        taskList.removeTaskItem(0);
        n = taskList.getTaskItemsCount();
        assertEquals(1, n);
    }

    @Test
    void editingTaskItemAndChangingStatusChangesValues() {
        taskList = new TaskList();
        TaskItem taskItem1 = null;
        TaskItem taskItem2 = null;

        try {
            taskItem1 = new TaskItem("some title", "some description",
                                     new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        try {
            Date taskItemDueDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            taskItem2 = new TaskItem("another title", "another description",
                                     taskItemDueDate);
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        taskList.addTaskItem(taskItem1);
        taskList.editTaskItem(taskItem2, 0);
        taskList.changeTaskItemComplitedStatus(0);
        assertNotEquals(taskItem1.getTitle(), taskList.getTaskItem(0).getTitle());
        assertNotEquals(taskItem1.getDescription(), taskList.getTaskItem(0).getDescription());
        assertNotEquals(taskItem1.getDueDate(), taskList.getTaskItem(0).getDueDate());
        assertNotEquals(taskItem1.isCompleted(), taskList.getTaskItem(0).isCompleted());
    }

    @Test
    void gettingTaskItemSucceedsWithValidIndex() {
        taskList = new TaskList();
        TaskItem taskItem = null;

        try {
            taskItem = new TaskItem("some title", "some description",
                    new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        taskList.addTaskItem(taskItem);
        try {
            taskItem = taskList.getTaskItem(0);
            assertNotNull(taskItem);
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }
    }

    @Test
    void gettingTaskItemFailsWithInvalidIndex() {
        try {
            taskList.getTaskItem(1000);
            fail("Exception not thrown");
        }
        catch(Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void removingTaskItemSucceedsWithValidIndex() {
        taskList = new TaskList();
        TaskItem taskItem = null;

        try {
            taskItem = new TaskItem("some title", "some description",
                    new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }

        taskList.addTaskItem(taskItem);
        try {
            taskList.removeTaskItem(0);
            assertEquals(0, taskList.getTaskItemsCount());
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }
    }

    @Test
    void removingTaskItemFailsWithInvalidIndex() {
        try {
            taskList.removeTaskItem(1000);
            fail("Exception not thrown");
        }
        catch(Exception e) {
            assertTrue(true);
        }
    }
}