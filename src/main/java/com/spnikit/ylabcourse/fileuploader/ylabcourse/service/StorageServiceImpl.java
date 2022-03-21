package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final PlayGameService playGameService;

    @Autowired
    public StorageServiceImpl(PlayGameService playGameService) {
        this.playGameService = playGameService;
    }

    public void load(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("Provided file is empty " + file.getOriginalFilename());
            throw new IllegalArgumentException("File is empty");
        }


        this.playGameService.playGameFromFile(file);
    }





}
