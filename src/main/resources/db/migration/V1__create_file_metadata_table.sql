-- Flyway migration: create file_metadata table

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS file_metadata (
  id uuid PRIMARY KEY,
  original_filename varchar(1024) NOT NULL,
  content_type varchar(255),
  size_bytes bigint NOT NULL,
  storage_path varchar(2048) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_file_metadata_created_at ON file_metadata(created_at);
