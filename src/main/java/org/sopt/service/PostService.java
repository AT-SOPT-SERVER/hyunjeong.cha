package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.dto.PostRequest;
import org.sopt.repository.PostRepository;
import org.sopt.service.validator.CreatedAtValidator;
import org.sopt.service.validator.TitleValidator;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    private final TitleValidator titleValidator = new TitleValidator();
    private final CreatedAtValidator createdAtValidator = new CreatedAtValidator();
    private final PostResolver postResolver = new PostResolver();

    private LocalDateTime createdAt;

    public void createPost(PostRequest request) throws IOException{
        createdAtValidator.createdAtValidate(createdAt);
        titleValidator.titleValidate(request.title(), postRepository.findTitle(request.title()));
        Post post = new Post(request.title());

        postRepository.save(post);
        saveFile(post);
        createdAt = LocalDateTime.now();
        System.out.println(post.getTitle());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(Long id) {
        return postRepository.delete(id);
    }

    public boolean updatePost(Long id, PostRequest request){
        titleValidator.titleValidate(request.title(), postRepository.findTitle(request.title()));
        Post post = postResolver.resolvePost(postRepository.findPostById(id));
        post.updateTitle(request.title());
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword){
        return postRepository.searchPostsByKeyword(keyword);
    }

    private void saveFile(Post post) throws IOException {
            Path dirPath = Paths.get("org/sopt/assets");
            Files.createDirectories(dirPath);

            Path filePath = dirPath.resolve("Post.txt");

            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile(), true);

            String line = post.getId() + "|" + post.getTitle() + System.lineSeparator();
            fileOutputStream.write(line.getBytes());

            fileOutputStream.close();
    }

    public void loadFile() throws IOException {
        postRepository.loadFile();
    }

}