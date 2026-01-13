package com.shubham.filestorage.util;

public final class FileConstants {

    private FileConstants() {}

    // max file size
    public static final int MAX_FILE_SIZE_MB = 20;
    public static final long MAX_FILE_SIZE_BYTES =
        MAX_FILE_SIZE_MB * 1024L * 1024L;

    // Allowed content types
    public static final String[] ALLOWED_CONTENT_TYPES = {
        "text/plain",
        "image/png",
        "image/jpeg",
        "image/jpg",
        "image/gif",
        "videp/avi",
        "video/mp4",
        "audio/mpeg",
        "audio/wav",
        "application/json",
        "application/pdf",                 
        "application/zip"
    };
}
