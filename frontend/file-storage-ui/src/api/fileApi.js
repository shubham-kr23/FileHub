import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/files";

export const uploadFile = (file) => {
  const formData = new FormData();
  formData.append("file", file);

  return axios.post(`${API_BASE_URL}/upload`, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};

export const getFiles = () => {
  return axios.get(API_BASE_URL);
};

export const downloadFile = (id) => {
  return axios.get(`${API_BASE_URL}/${id}/download`, {
    responseType: "blob",
  });
};

export const clearAll = (secret) => {
  return axios.delete(`${API_BASE_URL.replace('/files','/admin')}/clear`, {
    headers: {
      "X-ADMIN-SECRET": secret,
    },
  });
};
