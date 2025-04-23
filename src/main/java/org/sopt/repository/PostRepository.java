package org.sopt.repository;

import org.sopt.domain.Post;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.sopt.utils.IdGeneratorUtil.generateId;

@Repository
public class PostRepository {
    private final Map<Long, Post> postMap = new HashMap<>();

    public void save(Post post) {
        postMap.put(post.getId(), post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post findPostById(Long id) {
        if (postMap.get(id) != null)
            return postMap.get(id);

        return null;
    }

    public boolean delete(Long id) {
        if (postMap.get(id) != null) {
            postMap.remove(id);
            return true;
        }
        return false;
    }

    public boolean findTitle(String newTitle) {
        for (Post post : postMap.values()) {
            if (post.getTitle().equals(newTitle)) {
                return true;
            }
        }
        return false;
    }

    public List<Post> searchPostsByKeyword(String keyword){
        List<Post> searchPost = new ArrayList<>();
        for (Post post : postMap.values()) {
            if (post.getTitle().contains(keyword))
                searchPost.add(post);
        }
        return searchPost;
    }

    public void loadFile() throws IOException {
        File file = new File("org/sopt/assets/Post.txt");
        if (!file.exists()) return;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length < 2) continue;

            Long newId = generateId();
            String title = parts[1];

            postMap.put(newId, new Post(newId, title));
        }
        reader.close();
    }


}