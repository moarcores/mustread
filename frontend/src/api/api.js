export const getConfig = (token) => {
  const authToken = token || localStorage.getItem('token');
  return {
    headers: { Authorization: `Bearer ${authToken}` }
  }
};

export const API_URL = 'http://178.154.251.113:8080';
