import { useEffect, useState } from "react";
import { getFiles, downloadFile, clearAll } from "../api/fileApi";

function FileList() {
  const [files, setFiles] = useState([]);
  const [status, setStatus] = useState("");

  const fetchFiles = async () => {
    const response = await getFiles();
    setFiles(response.data);
  };

  useEffect(() => {
    fetchFiles();
  }, []);

  const handleDownload = async (id, filename) => {
    const response = await downloadFile(id);

    const blob = new Blob([response.data]);
    const url = window.URL.createObjectURL(blob);

    const link = document.createElement("a");
    link.href = url;
    link.download = filename;
    link.click();

    window.URL.revokeObjectURL(url);
  };

  const handleClear = async () => {
    const confirmClear = window.confirm(
      "This will permanently delete all files and metadata. Continue?"
    );
    if (!confirmClear) return;

    const secret = window.prompt("Enter admin secret to confirm:");
    if (!secret) {
      setStatus("Clear cancelled: no secret entered");
      return;
    }

    setStatus("Clearing...");
    try {
      await clearAll(secret);
      setStatus("All files and metadata cleared");
      setFiles([]);
    } catch (err) {
      const msg = err.response?.data || err.message || "Clear failed";
      setStatus("Clear failed: " + msg);
    }
  };

  return (
    <div>
      <h3>Uploaded Files</h3>
      <button onClick={handleClear} style={{ marginBottom: "8px" }}>
        CLEAR
      </button>
      <p>{status}</p>
      <ul>
        {files.map((file) => (
          <li key={file.id}>
            {file.originalFilename} ({file.size} bytes)
            <button
              onClick={() =>
                handleDownload(file.id, file.originalFilename)
              }
            >
              Download
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default FileList;
