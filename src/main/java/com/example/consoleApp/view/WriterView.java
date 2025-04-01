package com.example.consoleApp.view;
import com.example.consoleApp.controller.WriterController;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.model.Writer;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = new Scanner(System.in);

    public void showAllWriters() {
        List<Writer> writers = writerController.getAllWriters();
        if (writers.isEmpty()) {
            System.out.println("Writers not found");
        } else {
            System.out.println("Writer List: ");
            writers.forEach(writer ->
                    System.out.println(writer.getId() + " " + writer.getFirstName() + " " + writer.getLastName() + " " + writer.getStatus()));
        }
    }

    public void createWriter() {
        System.out.println("Enter firstName ");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastName ");
        String lastName = scanner.nextLine();

        writerController.createWriter(firstName, lastName);
    }

    public void updateWriter() {
        System.out.println("Enter update  writer id ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.println("Enter update firstName ");
        String firstName = scanner.nextLine();

        System.out.println("Enter update lastName ");
        String lastName = scanner.nextLine();

        System.out.println("Enter update status(ACTIVE, DELETE)");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        writerController.updateWriter(id, firstName, lastName, status);
    }

    public void deleteWriter() {
        System.out.println("Enter writer id to delete ");
        Long id = Long.parseLong(scanner.nextLine());
        writerController.deleteWriter(id);
    }
}