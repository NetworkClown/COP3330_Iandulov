package taskmanager;

import java.util.Date;

public class TaskItem {
    private String title;
    private String description;
    private Date dueDate;
    private Boolean isCompleted;

    public TaskItem(String title, String description, Date dueDate) throws EmptyTaskItemTitle {
        setTitle(title);
        setDescription(description);
        setDueDate(dueDate);
        setUncompleted();
    }

    public void setTitle(String title) throws EmptyTaskItemTitle {
        if (title.length() > 0) {
            this.title = title;
        }
        else {
            throw new EmptyTaskItemTitle("Empty task item title");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setUncompleted() {
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }
}

class EmptyTaskItemTitle extends Exception {
    EmptyTaskItemTitle() {

    }
    EmptyTaskItemTitle(String msg) {
        super(msg);
    }
}