package com.example.consoleApp.view;
import com.example.consoleApp.controller.LabelController;
import com.example.consoleApp.model.Label;
import com.example.consoleApp.model.Status;
import java.util.List;
import java.util.Scanner;

public class LabelView {
        private final LabelController labelController;
        private final Scanner scanner;

        public LabelView(LabelController labelController) {
                this.labelController = labelController;
                this.scanner = new Scanner(System.in);
        }

        public void showAllLabels() {
                List<Label> labels = labelController.getAllLabels();
                if (labels.isEmpty()) {
                        System.out.println("Labels not found");
                } else {
                        System.out.println("Label list: ");
                        labels.forEach(label -> {
                                System.out.println("ID " + label.getId());
                                System.out.println("Name " + label.getName());
                                System.out.println("Status " + label.getStatus());
                                System.out.println();
                        });
                }
        }

        public void createLabels() {
                System.out.println("Enter label name: ");
                String name = scanner.nextLine();
                labelController.createLabel(name);
        }

        public void updateLabel() {
                System.out.println("Enter update label id");
                Long id = Long.parseLong(scanner.nextLine());

                System.out.println("Enter update label");
                String name = scanner.nextLine();

                System.out.println("Enter new status (ACTIVE, DELETED)");
                Status status = Status.valueOf(scanner.nextLine().toUpperCase());

                labelController.updateLabel(id, name, status);
        }

        public void deleteLabel() {
                System.out.println("Enter label id to delete ");
                Long id = Long.parseLong(scanner.nextLine());
                labelController.deleteLabel(id);
        }
}
