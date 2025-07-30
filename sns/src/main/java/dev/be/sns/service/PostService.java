package dev.be.sns.service;

import dev.be.sns.exception.ErrorCode;
import dev.be.sns.exception.SnsApplicationException;
import dev.be.sns.model.AlarmArgs;
import dev.be.sns.model.AlarmType;
import dev.be.sns.model.Comment;
import dev.be.sns.model.Post;
import dev.be.sns.model.entity.*;
import dev.be.sns.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final LikeEntityRepository likeEntityRepository;
    private final CommentEntityRepository commentEntityRepository;
    private final AlarmEntityRepository alarmEntityRepository;

    @Transactional
    public void create(String title, String body, String userName) {

//        user find
        UserEntity userEntity = getUserEntity(userName);

        //        post save
        postEntityRepository.save(PostEntity.of(title, body, userEntity));

        //        return
    }

    @Transactional
    public Post modify(String title, String body, String userName, Integer postId) {
        UserEntity userEntity = getUserEntity(userName);

        PostEntity postEntity = getPostEntity(postId);

        if (postEntity.getUser() != userEntity) {
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s", userName, postId));
        }

        postEntity.setTitle(title);
        postEntity.setBody(body);

        return Post.fromEntity(postEntityRepository.saveAndFlush(postEntity));
    }

    @Transactional
    public void delete(String userName, Integer postId) {
        UserEntity userEntity = getUserEntity(userName);

        PostEntity postEntity = getPostEntity(postId);

        if (postEntity.getUser() != userEntity) {
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s", userName, postId));
        }

        postEntityRepository.delete(postEntity);
    }

    public Page<Post> list(Pageable pageable) {
        return postEntityRepository.findAll(pageable).map(Post::fromEntity);
    }

    public Page<Post> my(String userName, Pageable pageable) {
        UserEntity userEntity = getUserEntity(userName);

        return postEntityRepository.findAllByUser(userEntity, pageable).map(Post::fromEntity);
    }

    @Transactional
    public void like(String userName, Integer postId) {
        UserEntity userEntity = getUserEntity(userName);

        PostEntity postEntity = getPostEntity(postId);

        //        check liked -> throw
        likeEntityRepository.findByUserAndPost(userEntity, postEntity)
                .ifPresent(it -> {
                    throw new SnsApplicationException(ErrorCode.ALREADY_LIKED, String.format("userName %s already like post %d", userName, postId));
                });

        likeEntityRepository.save(LikeEntity.of(userEntity, postEntity));

        alarmEntityRepository.save(AlarmEntity.of(postEntity.getUser(), AlarmType.NEW_LIKE_ON_POST, new AlarmArgs(userEntity.getId(), postEntity.getId())));
    }

    @Transactional(readOnly = true)
    public int likeCount(Integer postId) {
        PostEntity postEntity = getPostEntity(postId);

        //        return likeEntityRepository.findAllByPost(postEntity).size();
        return likeEntityRepository.countByPost(postEntity);
    }

    @Transactional
    public void comment(Integer postId, String userName, String comment) {
        UserEntity userEntity = getUserEntity(userName);
        PostEntity postEntity = getPostEntity(postId);

        commentEntityRepository.save(CommentEntity.of(userEntity, postEntity, comment));

        alarmEntityRepository.save(AlarmEntity.of(postEntity.getUser(), AlarmType.NEW_COMMENT_ON_POST, new AlarmArgs(userEntity.getId(), postEntity.getId())));
    }

    public Page<Comment> getComment(Integer postId, Pageable pageable) {
        PostEntity postEntity = getPostEntity(postId);

        return commentEntityRepository.findAllByPost(postEntity, pageable).map(Comment::fromEntity);
    }

    /**
     *
     * @param userName
     * @throws SnsApplicationException - USER_NOT_FOUND
     * @return
     */
    private UserEntity getUserEntity(String userName) {
        return userEntityRepository.findByUserName(userName)
                .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));
    }

    /**
     *
     * @param postId
     * @throws SnsApplicationException - POST_NOT_FOUND
     * @return
     */
    private PostEntity getPostEntity(Integer postId) {
        return postEntityRepository.findById(postId)
                .orElseThrow(() -> new SnsApplicationException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));
    }

}
