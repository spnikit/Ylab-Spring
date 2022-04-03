package com.spnikit.ylabcourse.service;

import java.util.List;

public interface DBStorageService<T> {

    void save(T t);
    List<T> getAll();
    T getLast();
}
