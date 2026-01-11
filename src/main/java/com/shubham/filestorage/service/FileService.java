package com.shubham.filestorage.service;

import com.shubham.filestorage.model.FileMetadata;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface FileService {

    FileMetadata upload(MultipartFile file);

    List<FileMetadata> getAllFiles();

    Resource download(UUID id);

    FileMetadata getMetadata(UUID id);

    /**
     * Clear all metadata and stored files. Intended for local/dev use only and protected by an admin secret.
     */
    void clearAll();
}
