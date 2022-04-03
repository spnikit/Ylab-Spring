package com.spnikit.ylabcourse.controller;


import com.spnikit.ylabcourse.service.FileUploadImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@RequestMapping("file")
@Controller
public class FileUploadController {


    private final FileUploadImpl storageServiceImpl;

    @Autowired
    public FileUploadController(FileUploadImpl storageServiceImpl) {
        this.storageServiceImpl = storageServiceImpl;
    }


    @GetMapping
    public String showStartUpPage(){
        return "uploadForm";
    }

    @PostMapping("upload")
    public String handleFormFileUpload(@RequestParam MultipartFile file,
                                       RedirectAttributes redirectAttributes){

        log.info("Uploading file " + file.getOriginalFilename());
        storageServiceImpl.load(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/file";

    }
}
