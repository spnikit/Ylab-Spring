package com.spnikit.ylabcourse.game;

import java.util.Optional;

public interface GameplayToFileMapper<T> {

    void writeToFile(String filename, T t);

    Optional<T> readFromFile(String filename);
}
