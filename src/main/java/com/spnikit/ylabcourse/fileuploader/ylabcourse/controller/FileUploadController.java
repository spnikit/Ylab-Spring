package com.spnikit.ylabcourse.fileuploader.ylabcourse.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@RequestMapping
@Controller
public class FileUploadController {

    @GetMapping
    public String showStartUpPage(){

        return "uploadForm";
    }

    @PostMapping("/upload-file")
    public String handleFormFileUpload(@RequestParam MultipartFile file,
                                       RedirectAttributes redirectAttributes){


        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";

    }


}
