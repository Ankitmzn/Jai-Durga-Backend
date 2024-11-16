import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export const registerUser = (userData) => api.post('/register', userData);
export const loginUser = (credentials) => api.post('/login', credentials);
export const bookService = (bookingData) => api.post('/booking', bookingData);

export default api;
