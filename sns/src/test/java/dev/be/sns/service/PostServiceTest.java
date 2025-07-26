package dev.be.sns.service;

import dev.be.sns.exception.ErrorCode;
import dev.be.sns.exception.SnsApplicationException;
import dev.be.sns.model.entity.PostEntity;
import dev.be.sns.model.entity.UserEntity;
import dev.be.sns.repository.PostEntityRepository;
import dev.be.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockitoBean
    private PostEntityRepository postEntityRepository;

    @MockitoBean
    private UserEntityRepository userEntityRepository;

    @Test
    void 포스트_작성이_성공한_경우() {
        String title = "title";
        String body = "body";
        String userName = "userName";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(mock(UserEntity.class)));
        when(postEntityRepository.save(any())).thenReturn(mock(PostEntity.class));

        assertThatCode(() -> postService.create(title, body, userName))
                .doesNotThrowAnyException();
    }

    @Test
    void
    포스트_작성시_요청한_유저가_존재하지_않는_경우() {
        String title = "title";
        String body = "body";
        String userName = "userName";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(postEntityRepository.save(any())).thenReturn(mock(PostEntity.class));

        assertThatCode(() -> postService.create(title, body, userName))
                .hasCauseInstanceOf(SnsApplicationException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.USER_NOT_FOUND);
    }
}
