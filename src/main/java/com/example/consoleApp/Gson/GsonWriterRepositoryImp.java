package com.example.consoleApp.Gson;

import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GsonWriterRepositoryImp implements WriterRepository {
    private final Gson gson = new Gson();
    private final String file = "writers.json";


    @Override
    public void save(Writer writer) {
//        List<Writer> writers = getAll();
        writers.add(writer);
        writeToFile(writers);
    }


    private void writeToFile(List<Writer> writers) {
        try {
            Files.write(Paths.get(file), gson.toJson(writers).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
