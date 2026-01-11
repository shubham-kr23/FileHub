package com.shubham.filestorage.util;

import com.shubham.filestorage.exception.InvalidFileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Component
public class FileValidator {

    public void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("File is empty");
        }

        if (file.getSize() > FileConstants.MAX_FILE_SIZE_BYTES) {
            throw new InvalidFileException("File size exceeds the maximum allowed size of " + FileConstants.MAX_FILE_SIZE_MB + " MB");
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw new InvalidFileException("Unknown content type");
        }

        boolean allowed = Arrays.asList(FileConstants.ALLOWED_CONTENT_TYPES).contains(contentType.toLowerCase());
        if (!allowed) {
            throw new InvalidFileException("Content type not allowed: " + contentType);
        }
    }
}
