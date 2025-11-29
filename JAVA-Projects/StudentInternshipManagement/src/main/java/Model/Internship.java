package Model;

import java.sql.Date;

public class Internship {
    private int id;
    private String title;
    private String description;
    private int companyId;
    private Date deadline;

    public Internship() {
    }

    public Internship(String title, String description, int companyId, Date deadline) {
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.deadline = deadline;
    }

    public Internship(int id, String title, String description, int companyId, Date deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
