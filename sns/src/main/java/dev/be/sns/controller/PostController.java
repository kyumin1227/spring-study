package dev.be.sns.controller;

import dev.be.sns.controller.request.PostCommentRequest;
import dev.be.sns.controller.request.PostCreateRequest;
import dev.be.sns.controller.request.PostModifyRequest;
import dev.be.sns.controller.response.CommentResponse;
import dev.be.sns.controller.response.PostResponse;
import dev.be.sns.controller.response.Response;
import dev.be.sns.model.Post;
import dev.be.sns.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PutMapping("{postId}")
    public Response<PostResponse> modify(@PathVariable Integer postId,
                                         @RequestBody PostModifyRequest request,
                                         Authentication authentication) {

        Post post = postService.modify(request.getTitle(), request.getBody(), authentication.getName(), postId);

        return Response.success(PostResponse.fromPost(post));
    }

    @DeleteMapping("{postId}")
    public Response<Void> delete(@PathVariable Integer postId,
                                 Authentication authentication) {
        postService.delete(authentication.getName(), postId);

        return Response.success();
    }

    @GetMapping
    public Response<Page<PostResponse>> list(Pageable pageable, Authentication authentication) {
        return Response.success(postService.list(pageable).map(PostResponse::fromPost));
    }

    @GetMapping("/my")
    public Response<Page<PostResponse>> my(Pageable pageable, Authentication authentication) {
        return Response.success(postService.my(authentication.getName(), pageable).map(PostResponse::fromPost));
    }

    @PostMapping("/{postId}/likes")
    public Response<Void> like(@PathVariable Integer postId, Authentication authentication) {
        postService.like(authentication.getName(), postId);
        return Response.success();
    }

    @GetMapping("/{postId}/likes")
    public Response<Long> likeCount(@PathVariable Integer postId, Authentication authentication) {
        return Response.success(postService.likeCount(postId));
    }

    @PostMapping("/{postId}/comments")
    public Response<Void> comment(@PathVariable Integer postId,
                                  @RequestBody PostCommentRequest request,
                                  Authentication authentication) {

        postService.comment(postId, authentication.getName(), request.getComment());
        return Response.success();
    }

    @GetMapping("/{postId}/comments")
    public Response<Page<CommentResponse>> comment(@PathVariable Integer postId,
                                                   Pageable pageable,
                                                   Authentication authentication) {

        return Response.success(postService.getComment(postId, pageable).map(CommentResponse::fromComment));
    }
}
