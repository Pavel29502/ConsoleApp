package com.example.consoleApp.controller;
import com.example.consoleApp.model.Label;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.repository.LabelRepository;
import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void showAllLabels() {
        List<Label> labels = labelRepository.getAll();
        if (labels.isEmpty()) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Label list: ");
            labels.forEach(label -> {
                System.out.println("ID: " + label.getId());
                System.out.println("Name: " + label.getName());
                System.out.println("Status: " + label.getStatus());
                System.out.println();
            });
        }
    }

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public void createLabel(String name) {
        Long newId = labelRepository.getAll().stream()
                .mapToLong(Label::getId)
                .max().orElse(0L) + 1;
        Label label = new Label(newId, name, Status.ACTIVE);
        labelRepository.save(label);
        System.out.println("Label created " + label);
    }

    public void updateLabel(Long id, String name, Status status) {
        labelRepository.getAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst()
                .ifPresent(label -> {
                    label.setName(name);
                    label.setStatus(status);
                    labelRepository.update(label);
                    System.out.println("Label updated with id " + id);
                });
    }

    public void deleteLabel(Long id) {
        labelRepository.getAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst()
                .ifPresent(label -> {
                    label.setStatus(Status.DELETED);
                    labelRepository.update(label);
                    System.out.println("Label deleted with id " + id);
                });
    }
}

