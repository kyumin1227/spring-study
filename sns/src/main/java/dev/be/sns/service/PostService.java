package dev.be.sns.service;

import dev.be.sns.exception.ErrorCode;
import dev.be.sns.exception.SnsApplicationException;
import dev.be.sns.model.entity.PostEntity;
import dev.be.sns.repository.PostEntityRepository;
import dev.be.sns.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public void create(String title, String body, String userName) {

//        user find
        userEntityRepository.findByUserName(userName)
                .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));

//        post save
        postEntityRepository.save(new PostEntity());

//        return
    }
}
