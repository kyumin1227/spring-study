package dev.be.sns.controller;

import dev.be.sns.controller.request.PostCreateRequest;
import dev.be.sns.controller.request.PostModifyRequest;
import dev.be.sns.controller.response.PostResponse;
import dev.be.sns.controller.response.Response;
import dev.be.sns.model.Post;
import dev.be.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public Response<Void> create(@RequestBody PostCreateRequest request,
                                 Authentication authentication) {

        postService.create(request.getTitle(), request.getBody(), authentication.getName());

        return Response.success();
    }

    @PostMapping("{postId}")
    public Response<PostResponse> modify(@PathVariable Integer postId,
                                         @RequestBody PostModifyRequest request,
                                         Authentication authentication) {

        Post post = postService.modify(request.getTitle(), request.getBody(), authentication.getName(), postId);

        return Response.success(PostResponse.fromPost(post));
    }
}
