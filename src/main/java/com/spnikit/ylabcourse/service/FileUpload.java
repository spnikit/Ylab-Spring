package com.spnikit.ylabcourse.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpload {

    void load(MultipartFile file);
}
