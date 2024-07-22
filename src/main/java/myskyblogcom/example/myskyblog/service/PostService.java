package myskyblogcom.example.myskyblog.service;


import myskyblogcom.example.myskyblog.domain.Post;
import myskyblogcom.example.myskyblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

//    private final SpringDataJpaMemberRepository personRepository;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findPost() {
        return postRepository.findAll();
    }
    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }



    public Post join(Post post) {
        //CheckDuplicatedUsername(person);
        return postRepository.save(post);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }


}
