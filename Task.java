package taskflow;

import java.sql.Date;

public class Task {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String status;
    private Date deadline;

    public Task(int userId, String title, String description, String status, Date deadline) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }
}
