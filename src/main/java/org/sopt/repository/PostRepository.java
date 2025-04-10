package org.sopt.repository;

import org.sopt.domain.Post;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void loadFile(){
        try {
            File file = new File("org/sopt/assets/Post.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 2) continue;

                Long id = Long.parseLong(parts[0]);
                String title = parts[1];

                postMap.put(id, new Post(id, title));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("파일 불러오기 중 오류 발생: " + e.getMessage());
        }
    }

}