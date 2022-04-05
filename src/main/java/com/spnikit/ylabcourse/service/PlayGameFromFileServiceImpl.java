package com.spnikit.ylabcourse.service;

import com.spnikit.ylabcourse.game.GameFromFile;
import com.spnikit.ylabcourse.game.model.Gameplay;
import com.spnikit.ylabcourse.game.mappers.GameplayToJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.Executors;


@Service
@Slf4j
public class PlayGameFromFileServiceImpl implements PlayGameFromFileService {

    @Override
    public void play(MultipartFile file) {
        final var pool = Executors.newFixedThreadPool(1);

        try (InputStream inputStream = file.getInputStream()) {

            Optional<Gameplay> gameplay = new GameplayToJsonMapper().readFromStream(inputStream);

            pool.submit(() -> gameplay.ifPresentOrElse(game -> new GameFromFile().play(game),
                    () -> log.error("Gameplay object is not valid or not present")));

            pool.shutdown();
        } catch (IOException e) {
            log.error("Can't read stream from file", file.getOriginalFilename());
            e.printStackTrace();
        }
    }
}
