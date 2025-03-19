package com.example.consoleApp.repository.Gson;

import com.example.consoleApp.model.Label;
import com.example.consoleApp.repository.LabelRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final Gson gson = new Gson();
    private final String file = "labels.json";

    private Long getMaxId(List<Label> labels) {
        return labels.stream()
                .mapToLong(Label::getId)
                .max().orElse(0) + 1;
    }

    private List<Label> getAllLabelsInternal() {
        Path filePath = Paths.get(file);
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        try {
            String json = Files.readString(filePath).trim();
            if (json.isEmpty()) return new ArrayList<>();
            List<Label> labels = gson.fromJson(json, new TypeToken<List<Label>>() {}.getType());
            return labels != null ? labels : new ArrayList<>();
         } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeToFile(List<Label> labels) {
        try {
            Files.write(Paths.get(file), gson.toJson(labels).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Label labelToSave) {
        List<Label> labels = getAllLabelsInternal();
        Long newId = getMaxId(labels);
        labelToSave.setId(newId);
        labels.add(labelToSave);
        writeToFile(labels);
    }

    public List<Label> getAll() {
        return getAllLabelsInternal();
    }

    public void update(List<Label> labels) {
        writeToFile(labels);
    }


}
