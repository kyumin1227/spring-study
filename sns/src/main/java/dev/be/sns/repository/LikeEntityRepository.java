package dev.be.sns.repository;

import dev.be.sns.model.entity.LikeEntity;
import dev.be.sns.model.entity.PostEntity;
import dev.be.sns.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeEntityRepository extends JpaRepository<LikeEntity, Integer> {

    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);

    List<LikeEntity> findAllByPost(PostEntity post);

//    경량 쿼리
//    @Query(value = "SELECT COUNT(*) FROM LikeEntity entity WHERE entity.post = :post")
//    Integer countByPost(@Param("post") PostEntity post);

    long countByPost(PostEntity post);

    @Transactional
    @Modifying
    @Query("UPDATE LikeEntity entity SET entity.deletedAt = CURRENT_TIMESTAMP where entity.post = :post")
    void deleteAllByPost(@Param("post") PostEntity postEntity);
}
