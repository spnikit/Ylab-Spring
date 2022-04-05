package com.spnikit.ylabcourse.game.model;

import com.spnikit.ylabcourse.game.model.Token;

import java.util.Arrays;
import java.util.stream.Stream;

public class GameBoard {
    private final String[][] board = new String[][]{
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}
    };

    public void acceptMove(int xPoint, int yPoint, Token token) throws IllegalArgumentException {
        if (xPoint < 0 || xPoint > 2 || yPoint < 0 || yPoint > 2) {
            throw new IllegalArgumentException("Значение координаты должно быть от 0 до 2!");
        }


        if (!this.board[yPoint][xPoint].equals("-")) {
            throw new IllegalArgumentException("Ход в данную клетку уже был сделан!");
        }


        this.board[yPoint][xPoint] = token.toString();
    }

    public boolean isWinner() {
        var b = this.board;
        String X = Token.X.toString();
        String O = Token.O.toString();

        if (b[0][0].equals(b[0][1]) && b[0][1].equals(b[0][2]) && (b[0][1].equals(X) || b[0][1].equals(O))) {
            return true;
        } else if (b[1][0].equals(b[1][1]) && b[1][1].equals(b[1][2]) && (b[1][1].equals(X) || b[1][1].equals(O))) {
            return true;
        } else if (b[2][0].equals(b[2][1]) && b[2][1].equals(b[2][2]) && (b[2][1].equals(X) || b[2][1].equals(O))) {
            return true;
        } else if (b[0][0].equals(b[1][0]) && b[1][0].equals(b[2][0]) && (b[1][0].equals(X) || b[1][0].equals(O))) {
            return true;
        } else if (b[0][1].equals(b[1][1]) && b[1][1].equals(b[2][1]) && (b[1][1].equals(X) || b[1][1].equals(O))) {
            return true;
        } else if (b[0][2].equals(b[1][2]) && b[1][2].equals(b[2][2]) && (b[1][2].equals(X) || b[1][2].equals(O))) {
            return true;
        } else if (b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2]) && (b[1][1].equals(X) || b[1][1].equals(O))) {
            return true;
        } else return b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0]) && (b[1][1].equals(X) || b[1][1].equals(O));
    }

    public boolean isDraw() {
        return Arrays.stream(board).flatMap(Stream::of).noneMatch(v -> v.equals("-"));
    }

    public void printBoard() {
//        Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);

        System.out.printf("""
                        Поле текущей игры
                        -----------------
                               0  1  2
                            0  %s  %s  %s
                            1  %s  %s  %s
                            2  %s  %s  %s
                        -----------------
                         """,
                this.board[0][0],
                this.board[0][1],
                this.board[0][2],
                this.board[1][0],
                this.board[1][1],
                this.board[1][2],
                this.board[2][0],
                this.board[2][1],
                this.board[2][2]
        );
    }

    public String[] get1DBoard() {
        return Arrays.stream(board).flatMap(Stream::of).toArray(String[]::new);
    }
}
