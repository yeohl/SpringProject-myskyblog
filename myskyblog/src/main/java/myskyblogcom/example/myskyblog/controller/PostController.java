package myskyblogcom.example.myskyblog.controller;


import jakarta.servlet.http.HttpServletRequest;
import myskyblogcom.example.myskyblog.domain.Post;
import myskyblogcom.example.myskyblog.repository.PostRepository;
import myskyblogcom.example.myskyblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;



@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.findPost();
        model.addAttribute("posts", posts);
        return "welcome";
    }

    @GetMapping("/newPost")
    public String upload() { return "newPost"; }

    @GetMapping("/movieList")
    public String show() {return "movieList";}

    @GetMapping("/editPost/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        System.out.println("showUpdateForm 메서드 실행");
        Post editPost = postRepository.findById(id).orElse(null);
        System.out.println("editPost = " + editPost);
        model.addAttribute("post", editPost);
        return "editPost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content, Model model) {
        System.out.println("updatePost 메서드 실행");
        Optional<Post> result = postService.findOne(id);
        if(result.isPresent()) {
            Post existingPost = result.get();
            existingPost.setTitle(title);
            existingPost.setContent(content);
            postService.save(existingPost);
            model.addAttribute("post", existingPost);
        }
        return "redirect:/";
    }



    @PostMapping("/newPost")
    public String upload(@RequestParam("title") String title,
                         @RequestParam("content") String content)
    {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);


        postService.join(post);
        return "redirect:/";
    }

    @GetMapping("/deletePost")
    public String deletePost(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println(id);
        this.postService.deletePost(Long.parseLong(id));
        return "redirect:/";
    }



}
