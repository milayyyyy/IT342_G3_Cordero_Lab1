import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
});

// Add token to requests
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export const authAPI = {
  register: (data) => apiClient.post('/auth/register', data),
  login: (data) => apiClient.post('/auth/login', data),
  logout: () => apiClient.post('/auth/logout'),
  verifyEmail: (email, token) => apiClient.post(`/auth/verify-email?email=${email}&token=${token}`),
  requestPasswordReset: (email) => apiClient.post(`/auth/request-password-reset?email=${email}`),
  resetPassword: (data) => apiClient.post('/auth/reset-password', data),
};

export const userAPI = {
  getMe: () => apiClient.get('/user/me'),
  updateProfile: (data) => apiClient.put('/user/me', data),
  searchUsers: (query) => apiClient.get(`/user/search?query=${query}`),
  getActivity: () => apiClient.get('/user/activity'),
  getRecentActivity: (hoursBack = 24) => apiClient.get(`/user/activity/recent?hoursBack=${hoursBack}`),
};

export default apiClient;

