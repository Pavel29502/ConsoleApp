package com.example.consoleApp.controller;

import com.example.consoleApp.repository.Gson.GsonWriterRepositoryImpl;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public WriterController() {
        this.writerRepository = new GsonWriterRepositoryImpl();
    }

    public void showAllWriters() {
        writerRepository.getAll()
                .forEach(writer -> System.out.println(
                        writer.getId() + " " +
                                writer.getFirstName() + " " +
                                writer.getLastName() + " " +
                                writer.getStatus()));
    }

    public void createWriter(Long id, String firstName, String lastName) {
        Writer writer = new Writer(id, firstName, lastName, new ArrayList<>(), Status.ACTIVE);
        writerRepository.save(writer);
        System.out.println("writer sohranen" + writer.getFirstName());
    }

    public void saveWriterToFile() {
        List<Writer> writers = writerRepository.getAll();
//        ((GsonWriterRepositoryImpl) writerRepository).writeToFile(writers);
        System.out.println("Writers sohraneni");
        writers.forEach(writer -> writerRepository.save(writer));
        System.out.println("writer sohranen");
    }

    public void deleteWriter(Long id) {
        List<Writer> writers = writerRepository.getAll();

        boolean found = false;
        for (Writer writer : writers) {
            if (writer.getId().equals(id)) {
                writer.setStatus(Status.DELETED);
                found = true;
                break;
            }
        }
        if(found) {
            ((GsonWriterRepositoryImpl) writerRepository).update(writers);
            System.out.println(" Writer with id " + id + " deleted");
        } else {
            System.out.println(" Writer with id " + id + " ne naiden");
        }
    }

    public void updateWriter(Long id, String firstName, String lastName, Status status) {
        List<Writer> writers = writerRepository.getAll();
        boolean updated = false;

        for (Writer writer : writers) {
            if(writer.getId().equals(id)) {
                writer.setFirstName(firstName);
                writer.setLastName(lastName);
                writer.setStatus(status);
                updated = true;
                break;
            }
        }

        if (updated) {
            ((GsonWriterRepositoryImpl) writerRepository).update(writers);
            System.out.println("Writer with id " + id + " updated");
        } else {
            System.out.println("Writer with id " + id + " not found");
        }
    }


}
