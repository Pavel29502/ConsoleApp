package com.example.consoleApp.Gson;

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

public class GsonWriterRepositoryImp implements WriterRepository {
    private final Gson gson = new Gson();
    private final String file = "writers.json";


//    @Override
//    public void save(Writer writer) {
//        List<Writer> writers = getAll();
//        writers.add(writer);
//        writeToFile(writers);
//    }

    public void save(Writer writer) {
        List<Writer> writers = getAll();

        for(int i = 0; i < writers.size(); i++) {
            if(writers.get(i).getId().equals(writer.getId())) {
                writers.set(i, writer);
                writeToFile(writers);
                return;
            }
        }
        writers.add(writer);
        writeToFile(writers);
    }

    public void saveAll(List<Writer> writers) {
        writeToFile(writers);
    }


    public void writeToFile(List<Writer> writers) {
        try {
            Files.write(Paths.get(file), gson.toJson(writers).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public List<Writer> getAll() {
//
//            Path fPath = Paths.get(file);
//
//            if(!Files.exists(fPath)) {
//                return new ArrayList<>();
//            }
//            try {
//                String json = Files.readString(fPath).trim();
//                return gson.fromJson(json, new TypeToken<List<Writer>>() {}.getType());
//            } catch (IOException e) {
//                e.printStackTrace();
//                return new ArrayList<>();
//            }
//    }
    public List<Writer> getAll() {
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
}
