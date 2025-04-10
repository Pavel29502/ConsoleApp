package com.example.consoleApp.model;
import java.util.List;

public class Post {
    private Long id;
    private String title;
    private String content;
    private List<Label> labels;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }

    public Post(Long id, String title, String content, List<Label> labels, Status status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.labels = labels;
        this.status = status;
    }

    public Post() {
    }
}
