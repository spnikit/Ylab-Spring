package com.spnikit.ylabcourse.repos;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import org.springframework.data.repository.CrudRepository;

public interface GameplayRepository extends CrudRepository<GameplayEntity, Long> {

    GameplayEntity findTopByOrderByIdDesc();
}
