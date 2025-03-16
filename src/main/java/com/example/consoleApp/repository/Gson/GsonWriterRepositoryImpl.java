package com.example.consoleApp.repository.Gson;

import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final Gson gson = new Gson();
    private final String file = "writers.json";

    public void save(Writer writerToSave) {
        List<Writer> existingWriters = getAllWritersInternal();
        Long generatedId = getMaxId(existingWriters);
        writerToSave.setId(generatedId);
        existingWriters.add(writerToSave);
        writeToFile(existingWriters);
    }

    private List<Writer> getAllWritersInternal() {
        Path fPath = Paths.get(file);

        if(!Files.exists(fPath)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(fPath).trim();

            if(json.isEmpty()) {
                return new ArrayList<>();
            }

            List<Writer> writers = gson.fromJson(json, new TypeToken<List<Writer>>() {}.getType());
            return writers != null ? writers : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return  new ArrayList<>();
        }
    }

    private void writeToFile(List<Writer> writers) {
        try {
            Files.write(Paths.get(file), gson.toJson(writers).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long getMaxId(List<Writer> writers) {
        return writers.stream()
                .mapToLong(Writer::getId)
                .max().orElse(0) + 1;
    }

    public List<Writer> getAll() {
        return getAllWritersInternal()
                .stream()
//                .filter(w -> w.getStatus().equals(Status.ACTIVE))
                .toList();
    }

    public void update(List<Writer> writers) {
        writeToFile(writers);
    }
}
