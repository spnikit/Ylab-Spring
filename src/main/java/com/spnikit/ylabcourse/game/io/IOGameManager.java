package com.spnikit.ylabcourse.game.io;

import java.util.Optional;
import java.util.OptionalInt;

public interface IOGameManager {
    Optional<String> readStringFromConsole();

    OptionalInt readIntFromConsole();

    void printToConsole(String text);

    void finish();

    void writeMatchResults(String results);
}
