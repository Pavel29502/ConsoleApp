package com.example.consoleApp.controller;

import com.example.consoleApp.model.Label;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.repository.Gson.GsonLabelRepositoryImpl;
import com.example.consoleApp.repository.LabelRepository;
import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public LabelController() {
        this.labelRepository = new GsonLabelRepositoryImpl();
    }

    public void showAllLabels() {
        List<Label> labels = labelRepository.getAll();
        labels.forEach(System.out::println);
    }

    public void createLabel(String name) {
        Label label = new Label(null, name, Status.ACTIVE);
        labelRepository.save(label);
        System.out.println("Label created " + label);
    }

    public void deleteLabel(Long id) {
        List<Label> labels = labelRepository.getAll();

        boolean found = false;
        for (Label label : labels) {
            if (label.getId().equals(id)) {
                label.setStatus(Status.DELETED);
                found = true;
                break;
            }
        }
        if (found) {
            ((GsonLabelRepositoryImpl) labelRepository).update(labels);
            System.out.println("Label with id " + id + " deleted (status DELETED)");
        } else {
            System.out.println("Label with id " + id + " not found");
        }
    }

    public void updateLabel(Long id, String name, Status status) {
        List<Label> labels = labelRepository.getAll();
        boolean updated = false;

        for (Label label : labels) {
            if (label.getId().equals(id)) {
                label.setName(name);
                label.setStatus(status);
                updated = true;
                break;
            }
        }

        if (updated) {
            ((GsonLabelRepositoryImpl) labelRepository).update(labels);
        } else {
            System.out.println("Label with id " + id + " not found");
        }
    }
}

