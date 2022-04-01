package com.spnikit.ylabcourse.repos;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import com.spnikit.ylabcourse.entities.StepEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StepRepository extends CrudRepository<StepEntity, Long> {

    List<StepEntity>findByGameplaye(GameplayEntity gameplay, Sort sort);
}
