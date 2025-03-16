package com.example.consoleApp;

import com.example.consoleApp.controller.WriterController;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.repository.Gson.GsonWriterRepositoryImpl;
import com.example.consoleApp.repository.WriterRepository;

public class Main {
    public static void main(String[] args) {
        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        WriterController writerController = new WriterController(writerRepository);
//        PostRepository postRepository = new GsonPostRepositoryImpl();
//        PostController postController = new PostController(postRepository);

////        writerController.sa(1L, "jora", "Dobrinin");
//        writerController.createWriter(1L, "Ivan", "Petrov");
//        writerController.createWriter(2L, "Jora", "Jorikov");
//        writerController.createWriter(3L, "Petr", "Haritonov");
////        postController.createPost(1L, "post1", "new info");
//
//
//        writerRepository.getAll().forEach(writer ->
//                System.out.println(writer.getId() + " " + writer.getFirstName() + " " + writer.getStatus()));
//
////        writerController.deleteWriter(2L);
//
//        writerRepository.getAll().forEach(writer ->
//                System.out.println(writer.getId() + " " + writer.getFirstName() + " " + writer.getStatus()));

//        writerController.updateWriter(2L, "Petr", "Jorikov", Status.ACTIVE);
//        writerController.deleteWriter(1L);
        writerController.updateWriter(2L, "Janna", "Novikova", Status.ACTIVE);
        writerController.showAllWriters();



        //L9mbda
//        NumericTest isEven = (n) -> (n % 2) == 0;
//
//        if(isEven.test(10)) System.out.println("4islo 10 4etnoe");
//        if(!isEven.test(9)) System.out.println("4islo 9 ne4etnoe");
//
//        NumericTest isNonNeg = (n) -> n >= 0;
//
//        if(isNonNeg.test(1)) System.out.println("4islo  1 neotricatelnoe");
//        if(!isNonNeg.test(-1)) System.out.println("4islo -1 otricatelnoe");

//        ByteBuffer buf = ByteBuffer.allocate(10);
//        buf.put((byte) 3);
//        System.out.println(buf.get());
//        buf.flip();
//        System.out.println(buf.get());


//        List<Integer> numbers = Arrays.asList(2, 4, 8, 9, 3);
//
//        numbers.stream()
//                .filter(n -> n % 2 == 0)
//                .sorted()
//
//                .forEach(n -> System.out.println(n));
    }
}
