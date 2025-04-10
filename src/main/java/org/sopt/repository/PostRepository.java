package org.sopt.repository;

import org.sopt.domain.Post;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    public boolean delete(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public boolean findTitle(String newTitle){
        for (Post post : postList){
            if (post.getTitle().equals(newTitle)){
                return true;
            }
        }
        return false;
    }

    public List<Post> searchPostsByKeyword(String keyword){
        List<Post> searchPost = new ArrayList<>();
        for (Post post : postList){
            if (post.getTitle().contains(keyword))
                searchPost.add(post);
        }
        return searchPost;
    }

    public void loadFile(){
        try {
            System.out.println("1");
            File file = new File("org/sopt/assets/Post.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 2) continue;

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];

                System.out.println("2");
                postList.add(new Post(id, title));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("파일 불러오기 중 오류 발생: " + e.getMessage());
        }
    }

}