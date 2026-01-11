import { useState } from "react";
import { uploadFile } from "../api/fileApi";

function FileUpload({ onUploadSuccess }) {
  const [file, setFile] = useState(null);
  const [status, setStatus] = useState("");

  const handleUpload = async () => {
    if (!file) {
      setStatus("Please select a file");
      return;
    }

    try {
      await uploadFile(file);
      setStatus("Upload successful");
      setFile(null);
      onUploadSuccess();
    } catch (error) {
      setStatus(
        error.response?.data?.message || "Upload failed"
      );
    }
  };

  return (
    <div>
      <h3>Upload File</h3>
      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
      />
      <button onClick={handleUpload}>Upload</button>
      <p>{status}</p>
    </div>
  );
}

export default FileUpload;
