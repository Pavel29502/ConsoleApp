package com.example.consoleApp.controller;

import com.example.consoleApp.model.Label;
import com.example.consoleApp.model.Post;
import com.example.consoleApp.model.Status;
import com.example.consoleApp.repository.Gson.GsonPostRepositoryImpl;
import com.example.consoleApp.repository.LabelRepository;
import com.example.consoleApp.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;

public class PostController {
    private final PostRepository postRepository;
    private final LabelRepository labelRepository;

    public PostController(PostRepository postRepository, LabelRepository labelRepository) {
        this.postRepository = postRepository;
        this.labelRepository = labelRepository;
    }

    public void createPost(Long id, String title, String content, List<Long> labelId) {
        List<Label> labels = labelRepository.getAll().stream()
                .filter(label -> labelId.contains(label.getId()))
                .collect(Collectors.toList());

        Post post = new Post(id, title, content, labels, Status.ACTIVE);
        postRepository.save(post);
        System.out.println("Post saved " + post.getTitle());

    }

    public void deletePost(Long id) {
        List<Post> posts = postRepository.getAll();

        boolean found = false;
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                post.setStatus(Status.DELETED);
                found = true;
                break;
            }
        }
        if (found) {
            ((GsonPostRepositoryImpl) postRepository).update(posts);
            System.out.println(" Post with id " + id + " deleted");
        } else {
            System.out.println(" Post with id " + id + " deleted");
        }
    }

    public void showAllPosts() {
        postRepository.getAll().forEach(System.out::println);
    }

    public void updatePost(Long id ,String title, String content, Status status, List<Long> labelId) {
        List<Post> posts = postRepository.getAll();
        boolean updated = false;

        for (Post post : posts) {
            if(post.getId().equals(id)) {
                post.setTitle(title);
                post.setContent(content);
                post.setStatus(status);
//                updated = true;
//                break;
                List<Label> updatedLabels = labelRepository.getAll().stream()
                        .filter(label -> labelId.contains(label.getId()))
                        .collect(Collectors.toList());
                post.setLabels(updatedLabels);

                updated = true;
                break;
            }
        }

        if (updated) {
            ((GsonPostRepositoryImpl) postRepository).update(posts);
//            postRepository.save(posts);
            System.out.println("Writer with id " + id + " updated");
        } else {
            System.out.println("Writer with id " + id + " not found");
        }
    }

}
