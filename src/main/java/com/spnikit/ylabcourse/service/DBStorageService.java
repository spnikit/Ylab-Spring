package com.spnikit.ylabcourse.service;

import java.util.List;
import java.util.Optional;

public interface DBStorageService<T> {

    void save(T t);
    List<T> getAll();
    Optional<T> getLast();
    void deleteAll();
}
