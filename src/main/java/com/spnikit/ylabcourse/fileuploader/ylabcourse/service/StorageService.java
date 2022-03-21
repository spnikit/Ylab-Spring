package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;


import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Game;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Gameplay;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameplayToFileMapper;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameplayToJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
public class StorageService {


    public void load(MultipartFile file){
        if(file.isEmpty()){
            throw new IllegalArgumentException("file is empty");
        }

        playGameFromFile(file);
    }



    private void playGameFromFile(MultipartFile file){

        try(InputStream inputStream = file.getInputStream()) {

            Optional<Gameplay> gameplay = new GameplayToJsonMapper().readFromStream(inputStream);

            gameplay.ifPresentOrElse(game -> new Game().replay(game),
                    () -> log.error("Gameplay object is not valid or not present"));

        } catch (IOException e) {
            log.error("Can't read stream from file");
            e.printStackTrace();
        }
    }


}
