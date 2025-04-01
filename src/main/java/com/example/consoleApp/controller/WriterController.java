package com.example.consoleApp.controller;
import com.example.consoleApp.repository.Gson.GsonWriterRepositoryImpl;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;
import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public WriterController() {
        this.writerRepository = new GsonWriterRepositoryImpl();
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public void createWriter(String firstName, String lastName) {
        List<Writer> writersList = writerRepository.getAll();
        Long generateId = writersList.stream()
                .mapToLong(Writer::getId)
                .max()
                .orElse(0L) + 1;
        Writer writer = new Writer(generateId, firstName, lastName, null, Status.ACTIVE);
        writerRepository.save(writer);
        System.out.println("Writer saved " + writer.getFirstName() + " " + writer.getLastName());
    }

    public void updateWriter(Long id, String firstName, String lastName, Status status) {
        List<Writer> writersList = writerRepository.getAll();
        writersList.stream()
                .filter(writer -> writer.getId().equals(id))
                .findFirst()
                .ifPresent(writer -> {
                    writer.setFirstName(firstName);
                    writer.setLastName(lastName);
                    writer.setStatus(status);
                    writerRepository.update(writer);
                    System.out.println("Writer updated with id " + id);
                });
    }

    public void deleteWriter(Long id) {
        List<Writer> writersList = writerRepository.getAll();
        writersList.stream()
                .filter(writer -> writer.getId().equals(id))
                .findFirst()
                .ifPresent(writer -> {
                    writer.setStatus(Status.DELETED);
                    writerRepository.update(writer);
                    System.out.println("Writer deleted with id " + id);
                });
    }
}



