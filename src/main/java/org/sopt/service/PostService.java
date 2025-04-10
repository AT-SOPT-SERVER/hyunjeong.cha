package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.service.validator.CreatedAtValidator;
import org.sopt.service.validator.TitleValidator;
import org.sopt.utils.IdGeneratorUtil;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final TitleValidator titleValidator = new TitleValidator();
    private final CreatedAtValidator createdAtValidator = new CreatedAtValidator();
    private final PostResolver postResolver = new PostResolver();
    private final IdGeneratorUtil idGenerator = new IdGeneratorUtil();

    private LocalDateTime createdAt;

    public void createPost(String title) {
        createdAtValidator.createdAtValidate(createdAt);
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        Long newPostId = idGenerator.generateId();
        Post post = new Post(newPostId, title);

        postRepository.save(post);
        saveFile(post);
        createdAt = LocalDateTime.now();
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

    public boolean updatePost(Long id, String title){
        titleValidator.titleValidate(title, postRepository.findTitle(title));
        Post post = postResolver.resolvePost(postRepository.findPostById(id));
        post.update(title);
        return true;
    }

    public List<Post> searchPostsByKeyword(String keyword){
        return postRepository.searchPostsByKeyword(keyword);
    }

    private void saveFile(Post post) {
        try {
            Path dirPath = Paths.get("org/sopt/assets");
            Files.createDirectories(dirPath);

            Path filePath = dirPath.resolve("Post.txt");

            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile(), true);

            String line = post.getId() + "|" + post.getTitle() + System.lineSeparator();
            fileOutputStream.write(line.getBytes());

            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }

    public void loadFile(){
        postRepository.loadFile();
    }

}