package com.example.consoleApp.view;
import com.example.consoleApp.controller.PostController;
import com.example.consoleApp.model.Post;
import com.example.consoleApp.model.Status;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final PostController postController;
    private final Scanner scanner;

    public PostView(PostController postController) {
        this.postController = postController;
        this.scanner = new Scanner(System.in);
    }

    public void showAllPosts() {
        List<Post> posts = postController.getAllPosts();
        if (posts.isEmpty()) {
            System.out.println("posts not found");
        } else {
            System.out.println("Post list: ");
            posts.forEach(post -> {
                System.out.println("ID " + post.getId());
                System.out.println("Title " + post.getTitle());
                System.out.println("Content " + post.getContent());
                System.out.println("Status " + post.getStatus());

                if (post.getLabels() != null && !post.getLabels().isEmpty()) {
                    System.out.print("Labels: ");
                    post.getLabels().forEach(label -> System.out.print(label.getName() + " "));
                    System.out.println();
                } else {
                    System.out.println("Labels not found");
                }

                System.out.println();
            });
        }
    }

    public void createPost() {
        System.out.println("Enter post title ");
        String title = scanner.nextLine();

        System.out.println("Enter post content ");
        String content = scanner.nextLine();

        System.out.println("Enter label IDs  ");
        String[] labelIds = scanner.nextLine().split(",");
        List<Long> labels = List.of(labelIds).stream().map(Long::parseLong).toList();

        postController.createPost(null, title, content, labels);
    }

    public void updatePost() {
        System.out.println("Enter update post id ");
        Long id = Long.parseLong(scanner.nextLine());

        System.out.println("Enter update title ");
        String title = scanner.nextLine();

        System.out.println("Enter update content ");
        String content = scanner.nextLine();

        System.out.println("Enter update labels ");
        String[] labelIds = scanner.nextLine().split(",");
        List<Long> labels = List.of(labelIds).stream().map(Long::parseLong).toList();

        System.out.println("Enter update status (ACTIVE, DELETE)");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        postController.updatePost(id, title, content, labels, status);
    }

    public void deletePost() {
        System.out.println("Enter post id to delete ");
        Long id = Long.parseLong(scanner.nextLine());
        postController.deletePost(id);
    }
}
