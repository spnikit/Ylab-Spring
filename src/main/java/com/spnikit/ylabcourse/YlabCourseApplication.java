package com.spnikit.ylabcourse;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import com.spnikit.ylabcourse.entities.StepEntity;
import com.spnikit.ylabcourse.repos.GameplayRepository;
import com.spnikit.ylabcourse.repos.StepRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YlabCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlabCourseApplication.class, args);
    }


    @Bean
    public CommandLineRunner mappingDemo(
            GameplayRepository gameplayRepository,
            StepRepository stepRepository) {

        return args -> {

            var gameplayEntity = new GameplayEntity("sasha", "petya", null);

            gameplayRepository.save(gameplayEntity);

            stepRepository.save(new StepEntity(0, 0, 1, 1, gameplayEntity));
            stepRepository.save(new StepEntity(1, 1, 2, 2, gameplayEntity));
            stepRepository.save(new StepEntity(2, 2, 3, 1, gameplayEntity));
            stepRepository.save(new StepEntity(3, 3, 4, 2, gameplayEntity));


            System.out.println(gameplayRepository.findAll());

        };
    }
}
