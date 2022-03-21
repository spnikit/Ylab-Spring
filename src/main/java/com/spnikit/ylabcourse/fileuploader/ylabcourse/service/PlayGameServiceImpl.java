package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Game;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Gameplay;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameplayToJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.Executors;


@Service
@Slf4j
public class PlayGameServiceImpl implements PlayGameService {

    @Override
    public void playGameFromFile(MultipartFile file) {
        final var pool = Executors.newFixedThreadPool(1);

        try (InputStream inputStream = file.getInputStream()) {

            Optional<Gameplay> gameplay = new GameplayToJsonMapper().readFromStream(inputStream);

            pool.submit(() -> {

                gameplay.ifPresentOrElse(game -> new Game().replay(game),
                        () -> log.error("Gameplay object is not valid or not present"));

            });

            pool.shutdown();
        } catch (IOException e) {
            log.error("Can't read stream from file", file.getOriginalFilename());
            e.printStackTrace();
        }
    }
}
