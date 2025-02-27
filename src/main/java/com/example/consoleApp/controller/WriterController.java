package com.example.consoleApp.controller;

import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import com.example.consoleApp.repository.WriterRepository;

import java.util.ArrayList;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public void createWriter(Long id, String firstName, String lastName) {
        Writer writer = new Writer(id, firstName, lastName, new ArrayList<>(), Status.AСTIVE);
        writerRepository.save(writer);
        System.out.println("writer sohranen" + writer.getFirstName());
    }
}
