package com.example.consoleApp.model;

public class Label {
    private Long id;
    private String name;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Label(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Label() {
    }
}
