package com.shubham.filestorage.service.impl;

import com.shubham.filestorage.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Primary
public class LocalStorageService implements StorageService {

    private final Path baseDir;

    public LocalStorageService(@Value("${storage.local.base-dir:./storage}") String baseDir) throws Exception {
        this.baseDir = Paths.get(baseDir).toAbsolutePath().normalize();
        Files.createDirectories(this.baseDir);
    }

    @Override
    public String store(MultipartFile file, UUID id) throws Exception {
        String filename = id.toString() + "_" + file.getOriginalFilename();
        Path target = baseDir.resolve(filename);
        try (var in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return target.toString();
    }

    @Override
    public Resource loadAsResource(String storagePath) throws Exception {
        Path file = Paths.get(storagePath);
        if (!Files.exists(file) || !Files.isReadable(file)) {
            throw new java.nio.file.NoSuchFileException("File not found: " + storagePath);
        }
        try {
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new java.nio.file.NoSuchFileException("Could not read file: " + storagePath);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() throws Exception {
        // Delete all files/directories under baseDir but keep the baseDir itself
        try (var stream = Files.walk(this.baseDir)) {
            stream.filter(path -> !path.equals(this.baseDir))
                  .sorted(java.util.Comparator.reverseOrder())
                  .forEach(path -> {
                      try {
                          Files.deleteIfExists(path);
                      } catch (Exception e) {
                          throw new RuntimeException("Failed to delete: " + path + " -> " + e.getMessage(), e);
                      }
                  });
        }
    }
}
