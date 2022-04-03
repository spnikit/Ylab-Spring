package com.spnikit.ylabcourse.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class FileUploadImpl implements FileUpload {

    private final PlayGameFromFileService playGameService;

    @Autowired
    public FileUploadImpl(PlayGameFromFileService playGameService) {
        this.playGameService = playGameService;
    }

    public void load(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("Provided file is empty " + file.getOriginalFilename());
            throw new IllegalArgumentException("File is empty");
        }

        this.playGameService.play(file);
    }
}
