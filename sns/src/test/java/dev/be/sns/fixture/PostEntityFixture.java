package dev.be.sns.fixture;

import dev.be.sns.model.entity.PostEntity;
import dev.be.sns.model.entity.UserEntity;

public class PostEntityFixture {

    public static PostEntity get(String userName, Integer postId, Integer userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(userName);

        PostEntity result = new PostEntity();
        result.setId(postId);
        result.setUser(user);
        return result;
    }
}
