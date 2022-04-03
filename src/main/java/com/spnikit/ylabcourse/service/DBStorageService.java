package com.spnikit.ylabcourse.service;

import com.spnikit.ylabcourse.game.Gameplay;

import java.util.List;

public interface DBStorageService<T> {

    void save(T t);
    List<T> getGameplays();
    T getLastGameplay();
}
