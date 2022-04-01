package com.spnikit.ylabcourse.service;

import org.springframework.web.multipart.MultipartFile;

public interface PlayGameFromFileService {

    void play(MultipartFile file);
}
