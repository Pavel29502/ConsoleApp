package com.example.consoleApp;

import com.example.consoleApp.Gson.GsonPostRepositoryImpl;
import com.example.consoleApp.Gson.GsonWriterRepositoryImp;
import com.example.consoleApp.controller.PostController;
import com.example.consoleApp.controller.WriterController;
import com.example.consoleApp.repository.PostRepository;
import com.example.consoleApp.repository.WriterRepository;

public class Main {
    public static void main(String[] args) {
        WriterRepository writerRepository =new GsonWriterRepositoryImp();
        WriterController writerController = new WriterController(writerRepository);
        PostRepository postRepository = new GsonPostRepositoryImpl();
        PostController postController = new PostController(postRepository);

//        writerController.sa(1L, "jora", "Dobrinin");
        writerController.createWriter(1L, "Ivan", "Petrov");
        writerController.createWriter(2L, "Jora", "Jorikov");
        postController.createPost(1L, "post1", "new info");


        writerRepository.getAll().forEach(writer ->
                System.out.println(writer.getId() + " " + writer.getFirstName() + " " + writer.getStatus()));

        writerController.deleteWriter(2L);

        writerRepository.getAll().forEach(writer ->
                System.out.println(writer.getId() + " " + writer.getFirstName() + " " + writer.getStatus()));




    }
}
