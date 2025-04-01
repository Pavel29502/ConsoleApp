package com.example.consoleApp.controller;
import com.example.consoleApp.model.Label;
import com.example.consoleApp.model.Post;
import com.example.consoleApp.model.Status;
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

    public void showAllPosts() {
        postRepository.getAll().forEach(System.out::println);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public void updatePost(Long id, String title, String content, List<Long> labelIds, Status status) {
        postRepository.getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setTitle(title);
                    post.setContent(content);
                    post.setStatus(status);
                    post.setLabels(labelRepository.getAll().stream()
                            .filter(label -> labelIds.contains(label.getId()))
                            .collect(Collectors.toList()));
                    postRepository.update(post);
                    System.out.println("Post updated with id " + id);
                });
    }

    public void deletePost(Long id) {
        postRepository.getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> {
                    post.setStatus(Status.DELETED);
                    postRepository.update(post);
                    System.out.println("Post deleted with id " + id);
                });
    }
}
