export const getConfig = (token) => {
  return {
    headers: { Authorization: `Bearer ${token}` }
  }
};

export const API_URL = 'http://localhost:8080';
