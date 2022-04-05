package com.spnikit.ylabcourse.game.mappers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spnikit.ylabcourse.game.mappers.GameplayToFileMapper;
import com.spnikit.ylabcourse.game.model.Gameplay;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;


@Slf4j
public class GameplayToJsonMapper implements GameplayToFileMapper<Gameplay> {
    ObjectMapper mapper;

    public GameplayToJsonMapper() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeToFile(String filename, Gameplay gameplay) {
        try {
            mapper.writeValue(new File(Objects.requireNonNull(filename)), Objects.requireNonNull(gameplay));
        } catch (IOException e) {
            log.error("can't write JSON to file " + filename);
            e.printStackTrace();
        }
    }

    public Optional<Gameplay> readFromFile(String filename) {
        Optional<Gameplay> gameplay;
        try {
            gameplay = Optional.of(mapper.readValue(new File(Objects.requireNonNull(filename)), Gameplay.class));
        } catch (IOException e) {
            log.error("can't read file " + filename);
            gameplay = Optional.empty();
            e.printStackTrace();
        }
        return gameplay;
    }

    public Optional<Gameplay> readFromStream(InputStream stream) {

        Optional<Gameplay> gameplay;
        try {
            gameplay = Optional.of(mapper.readValue(Objects.requireNonNull(stream), Gameplay.class));
        } catch (IOException e) {
            log.error("can't read file from stream ");
            gameplay = Optional.empty();
            e.printStackTrace();
        }
        return gameplay;
    }
}
