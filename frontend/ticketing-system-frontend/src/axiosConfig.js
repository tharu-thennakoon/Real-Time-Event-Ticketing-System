import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/ticket-pool', // Base URL for backend API
  headers: {
    'Content-Type': 'application/json',
  },
});

export default axiosInstance;
