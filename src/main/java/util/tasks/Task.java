package util.tasks;

import util.enums.DifficultyEnum;

import java.util.Date;

public class Task {

    private final int id;
    private String name;
    private String assignedTo;
    private String assignedBy;
    private Date dateAssigned;
    private Date dateCompleted;
    private DifficultyEnum difficulty;
    private boolean allTimesGiven;

    public Task(int id, String name, String assignedTo, String assignedBy, Date dateAssigned, Date dateCompleted, DifficultyEnum difficulty, boolean allTimesGiven) {
        this.id = id;

        this.setName(name);
        this.setAssignedTo(assignedTo);
        this.setAssignedBy(assignedBy);
        this.setDateAssigned(dateAssigned);
        this.setDateCompleted(dateCompleted);
        this.setDifficulty(difficulty);
        this.setAllTimesGiven(allTimesGiven);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isAllTimesGiven() {
        return allTimesGiven;
    }

    public void setAllTimesGiven(boolean allTimesGiven) {
        this.allTimesGiven = allTimesGiven;
    }
}
