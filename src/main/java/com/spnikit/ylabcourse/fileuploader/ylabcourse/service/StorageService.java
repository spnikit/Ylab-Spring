package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StorageService {

    private final List<Path> files = new ArrayList<>();


    public void load(MultipartFile file){
        if(file.isEmpty()){
            throw new IllegalArgumentException("file is empty");

        }

        files.add(Paths.get(Objects.requireNonNull(file.getOriginalFilename())));
    }

    public List<Path> getFiles(){
        return this.files;
    }
}
