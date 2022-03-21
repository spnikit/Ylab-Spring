package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import org.springframework.web.multipart.MultipartFile;

public interface PlayGameService {

    void playGameFromFile(MultipartFile file);
}
