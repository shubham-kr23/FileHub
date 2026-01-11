package com.shubham.filestorage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface StorageService {

    /**
     * Store file and return a storage path string that can be used to retrieve it later.
     */
    String store(MultipartFile file, UUID id) throws Exception;

    /**
     * Load resource by storage path.
     */
    Resource loadAsResource(String storagePath) throws Exception;

    /**
     * Delete all objects/files managed by the storage service.
     */
    void deleteAll() throws Exception;
}
