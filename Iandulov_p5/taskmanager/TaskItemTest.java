package taskmanager;

import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    private TaskItem taskItem;

    @Test
    void creatingTaskItemFailsWithInvalidTitle() {
        try {
            taskItem = new TaskItem("", "some description",
                                    new Date(System.currentTimeMillis()));
            fail("Exception not thrown");
        }
        catch(Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void creatingTaskItemSucceedsWithValidTitle() {
        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }
    }

    @Test
    void settingTaskItemTitleFailsWithInvalidTitle() {
        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));
            taskItem.setTitle("");
            fail("Exception not thrown");
        }
        catch(Exception e) {
            assertTrue(true);
        }
    }

    @Test
    void settingTaskItemTitleSucceedsWithValidTitle() {
        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));
            taskItem.setTitle("another title");
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }
    }

    @Test
    @DisplayName("Checking all set/get, except for TaskItem.Title")
    void checkAllSetGetMethods() {
        try {
            taskItem = new TaskItem("some title", "some description",
                                    new Date(System.currentTimeMillis()));

            String taskItemDescription = "another description";
            taskItem.setDescription(taskItemDescription);
            assertEquals(taskItemDescription, taskItem.getDescription());

            Date taskItemDueDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            taskItem.setDueDate(taskItemDueDate);
            assertEquals(taskItemDueDate, taskItem.getDueDate());

            taskItem.setCompleted();
            assertTrue(taskItem.isCompleted());
            taskItem.setUncompleted();
            assertFalse(taskItem.isCompleted());
        }
        catch(Exception e) {
            fail("Exception was thrown");
        }
    }
}