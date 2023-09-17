package post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void enrollPost(ValidatePostRequestDto validatePostRequestDto) {
        Post createPost = Post.builder()
                .content(validatePostRequestDto.getContent())
                .title(validatePostRequestDto.getTitle())
                .create_ymd(LocalDateTime.now())
                .build();
        postRepository.save(createPost);
    }

    @Override
    public void deletePost(long post_pk) {
        postRepository.deleteById(post_pk);
    }
}