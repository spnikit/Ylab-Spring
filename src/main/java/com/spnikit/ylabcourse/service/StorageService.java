package com.spnikit.ylabcourse.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void load(MultipartFile file);
}
