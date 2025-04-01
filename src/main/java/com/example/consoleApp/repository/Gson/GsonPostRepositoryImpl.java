package com.example.consoleApp.repository.Gson;
import com.example.consoleApp.model.Post;
import com.example.consoleApp.repository.PostRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private final Gson gson = new Gson();
    private final String postFile = "post.json";

    private List<Post> getAllPostInternal() {
        Path fPath = Paths.get(postFile);

        if (!Files.exists(fPath)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(fPath).trim();

            if (json.isEmpty()) {
                return new ArrayList<>();
            }

            List<Post> posts = gson.fromJson(json, new TypeToken<List<Post>>() {
            }.getType());
            return posts != null ? posts : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Post> getAll() {
        return getAllPostInternal();
    }

    private Long getMxId(List<Post> posts) {
        return posts.stream()
                .mapToLong(Post::getId)
                .max().orElse(0) + 1;
    }

    private void writeToFile(List<Post> posts) {
        try {
            Files.write(Paths.get(postFile), gson.toJson(posts).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Post post) {
        List<Post> posts = getAllPostInternal();
        post.setId(getMxId(posts));
        posts.add(post);
        writeToFile(posts);
    }

    public void update(Post post) {
        List<Post> posts = getAllPostInternal();
        posts.stream()
                .filter(p -> p.getId().equals(post.getId()))
                .findFirst()
                .ifPresent(existingPost -> {
                    existingPost.setTitle(post.getTitle());
                    existingPost.setContent(post.getContent());
                    existingPost.setStatus(post.getStatus());
                    existingPost.setLabels(post.getLabels());
                    writeToFile(posts);
                });
    }
}

