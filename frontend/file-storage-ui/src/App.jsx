import FileUpload from "./components/FileUpload";
import FileList from "./components/FileList";
import { useRef } from "react";

function App() {
  const fileListRef = useRef();

  return (
    <div style={{ padding: "20px" }}>
      <h1>File Storage (Local)</h1>
      <FileUpload onUploadSuccess={() => window.location.reload()} />
      <hr />
      <div id="file-item">
        <FileList ref={fileListRef} />
      </div>
    </div>
  );
}

export default App;
