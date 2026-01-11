package com.shubham.filestorage.service.impl;

import com.shubham.filestorage.exception.FileNotFoundException;
import com.shubham.filestorage.exception.InvalidFileException;
import com.shubham.filestorage.model.FileMetadata;
import com.shubham.filestorage.repository.FileMetadataRepository;
import com.shubham.filestorage.service.FileService;
import com.shubham.filestorage.service.StorageService;
import com.shubham.filestorage.util.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final FileMetadataRepository repository;
    private final StorageService storageService;
    private final FileValidator validator;

    public FileServiceImpl(FileMetadataRepository repository,
                           StorageService storageService,
                           FileValidator validator) {
        this.repository = repository;
        this.storageService = storageService;
        this.validator = validator;
    }

    @Override
    public FileMetadata upload(MultipartFile file) {
        try {
            validator.validate(file);

            FileMetadata meta = new FileMetadata();
            // id will be assigned at persist time, but we need an id for file name
            UUID id = UUID.randomUUID();
            meta.setId(id);
            meta.setOriginalFilename(file.getOriginalFilename());
            meta.setContentType(file.getContentType());
            meta.setSize(file.getSize());

            String storagePath = storageService.store(file, id);
            meta.setStoragePath(storagePath);

            return repository.save(meta);
        } catch (InvalidFileException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public List<FileMetadata> getAllFiles() {
        return repository.findAll();
    }

    @Override
    public Resource download(UUID id) {
        FileMetadata meta = repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Metadata not found for id: " + id));
        try {
            return storageService.loadAsResource(meta.getStoragePath());
        } catch (Exception e) {
            throw new FileNotFoundException("File not found: " + e.getMessage());
        }
    }

    @Override
    public FileMetadata getMetadata(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Metadata not found for id: " + id));
    }

    @Override
    public void clearAll() {
        try {
            // delete files from storage first
            storageService.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear storage: " + e.getMessage(), e);
        }
        // then delete metadata records
        repository.deleteAll();
    }
}
