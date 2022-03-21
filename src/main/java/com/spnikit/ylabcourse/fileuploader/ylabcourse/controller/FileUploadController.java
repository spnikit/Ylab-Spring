package com.spnikit.ylabcourse.fileuploader.ylabcourse.controller;


import com.spnikit.ylabcourse.fileuploader.ylabcourse.service.StorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@RequestMapping
@Controller
public class FileUploadController {


    private final StorageServiceImpl storageServiceImpl;

    @Autowired
    public FileUploadController(StorageServiceImpl storageServiceImpl) {
        this.storageServiceImpl = storageServiceImpl;
    }


    @GetMapping
    public String showStartUpPage(){
        return "uploadForm";
    }

    @PostMapping("/upload-file")
    public String handleFormFileUpload(@RequestParam MultipartFile file,
                                       RedirectAttributes redirectAttributes){

        log.info("Uploading file " + file.getOriginalFilename());
        storageServiceImpl.load(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";

    }
}
