package com.example.consoleApp;
import com.example.consoleApp.controller.LabelController;
import com.example.consoleApp.controller.PostController;
import com.example.consoleApp.repository.Gson.GsonLabelRepositoryImpl;
import com.example.consoleApp.repository.Gson.GsonPostRepositoryImpl;
import com.example.consoleApp.view.LabelView;
import com.example.consoleApp.view.PostView;
import com.example.consoleApp.view.WriterView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WriterView writerView = new WriterView();
        PostView postView = new PostView(new PostController(new GsonPostRepositoryImpl(), new GsonLabelRepositoryImpl()));
        LabelView labelView = new LabelView(new LabelController(new GsonLabelRepositoryImpl()));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose menu for work:");
            System.out.println("1. Writers");
            System.out.println("2. Posts");
            System.out.println("3. Labels");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choicePojo = scanner.nextInt();
            scanner.nextLine();

            switch (choicePojo) {
                case 1:
                    showWritersMenu(writerView);
                    break;
                case 2:
                    showPostMenu(postView);
                    break;
                case 3:
                    showLabelMenu(labelView);
                    break;
                case 4:
                    System.out.println("Exiting program ");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void showWritersMenu(WriterView writerView) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWriter Menu:");
            System.out.println("1. Show writers");
            System.out.println("2. Create writer");
            System.out.println("3. Update writer");
            System.out.println("4. Delete writer");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    writerView.showAllWriters();
                    break;
                case 2:
                    writerView.createWriter();
                    break;
                case 3:
                    writerView.updateWriter();
                    break;
                case 4:
                    writerView.deleteWriter();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void showPostMenu(PostView postView) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPost Menu:");
            System.out.println("1. Show posts");
            System.out.println("2. Create post");
            System.out.println("3. Update post");
            System.out.println("4. Delete post");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    postView.showAllPosts();
                    break;
                case 2:
                    postView.createPost();
                    break;
                case 3:
                    postView.updatePost();
                    break;
                case 4:
                    postView.deletePost();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void showLabelMenu(LabelView labelView) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLabel Menu:");
            System.out.println("1. Show labels");
            System.out.println("2. Create label");
            System.out.println("3. Update label");
            System.out.println("4. Delete label");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    labelView.showAllLabels();
                    break;
                case 2:
                    labelView.createLabels();
                    break;
                case 3:
                    labelView.updateLabel();
                    break;
                case 4:
                    labelView.deleteLabel();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}