package com.spnikit.ylabcourse.repos;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameplayRepository extends CrudRepository<GameplayEntity, Long> {

    Optional<GameplayEntity> findTopByOrderByIdDesc();
}
