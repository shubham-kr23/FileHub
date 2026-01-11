package com.shubham.filestorage.controller;

import com.shubham.filestorage.model.FileMetadata;
import com.shubham.filestorage.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Upload
    @PostMapping("/upload")
    public ResponseEntity<FileMetadata> uploadFile(
            @RequestParam("file") MultipartFile file) {

        FileMetadata savedFile = fileService.upload(file);
        return ResponseEntity.ok(savedFile);
    }

    // List files
    @GetMapping
    public ResponseEntity<List<FileMetadata>> listFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }

    // Download
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") UUID id) {

        Resource resource = fileService.download(id);
        FileMetadata meta = fileService.getMetadata(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + meta.getOriginalFilename() + "\""
                )
                .header(HttpHeaders.CONTENT_TYPE, meta.getContentType())
                .body(resource);
    }
}
