package com.spnikit.ylabcourse.db.repos;

import com.spnikit.ylabcourse.db.entities.GameplayEntity;
import com.spnikit.ylabcourse.db.entities.StepEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StepRepository extends CrudRepository<StepEntity, Long> {

    List<StepEntity>findByGameplay(GameplayEntity gameplay, Sort sort);
}
