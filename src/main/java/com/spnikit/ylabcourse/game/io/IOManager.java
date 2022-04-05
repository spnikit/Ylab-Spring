package com.spnikit.ylabcourse.game.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.OptionalInt;

public class IOManager implements IOGameManager {
    private final BufferedReader reader;

    public IOManager() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Optional<String> readStringFromConsole() {

        Optional<String> line = readFromConsole();

        if (line.isEmpty()) {
            return Optional.empty();
        }

        String lineValue = line.get();

        if (lineValue.equals("")) {
            return Optional.empty();
        }

        return Optional.of(lineValue);

    }

    private Optional<String> readFromConsole() {

        try {
            String line = reader.readLine().trim();
            return Optional.of(line);
        } catch (IOException e) {
            return Optional.empty();
        }

    }

    public OptionalInt readIntFromConsole() {

        Optional<String> value = readFromConsole();

        if (value.isEmpty()) {

            return OptionalInt.empty();
        }

        return parseInt(value.get());
    }

    private OptionalInt parseInt(String s) {
        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    public void printToConsole(String text) {
        System.out.println(text);
    }


    public void finish() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMatchResults(String results) {
        try (var writer = Files.newBufferedWriter(Paths.get("./gameResults.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        ) {
            writer.write(results + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

