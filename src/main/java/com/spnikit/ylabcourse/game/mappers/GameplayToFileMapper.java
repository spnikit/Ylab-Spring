package com.spnikit.ylabcourse.game.mappers;

import java.util.Optional;

public interface GameplayToFileMapper<T> {

    void writeToFile(String filename, T t);

    Optional<T> readFromFile(String filename);
}
