package com.example.consoleApp;

import com.example.consoleApp.Gson.GsonWriterRepositoryImp;
import com.example.consoleApp.controller.WriterController;
import com.example.consoleApp.repository.WriterRepository;

public class Main {
    public static void main(String[] args) {
        WriterRepository writerRepository =new GsonWriterRepositoryImp();
        WriterController writerController = new WriterController(writerRepository);

//        writerController.sa(1L, "jora", "Dobrinin");
        writerController.createWriter(1L, "ss", "gg");
    }
}
