// package com.dkmo.integrationnextjs.controllers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.dkmo.integrationnextjs.utils.FilesSave;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping(value = "/files",produces = {"application/json"})
// public class FilesController {
//     @Autowired
//     private FilesSave filesSave;
//     @PostMapping("/save")
//     public String saveFile(@RequestParam("file")MultipartFile file){
//         return filesSave.saveFile(file);
//     }
// }
