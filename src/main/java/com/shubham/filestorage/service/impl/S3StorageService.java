package com.shubham.filestorage.service.impl;

import com.shubham.filestorage.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "S3")
public class S3StorageService implements StorageService {

    @Override
    public String store(MultipartFile file, UUID id) throws Exception {
        throw new UnsupportedOperationException("S3 storage is not configured in this build");
    }

    @Override
    public Resource loadAsResource(String storagePath) throws Exception {
        throw new UnsupportedOperationException("S3 storage is not configured in this build");
    }

    @Override
    public void deleteAll() throws Exception {
        throw new UnsupportedOperationException("S3 deleteAll is not implemented");
    }
}