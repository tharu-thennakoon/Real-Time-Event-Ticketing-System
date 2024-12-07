
import axios from 'axios';

const API_URL = 'http://localhost:5000/api';  // Adjust based on your backend API URL

export const getTicketStatus = async () => {
  try {
    const response = await axios.get(`${API_URL}/ticket-status`);
    return response.data;
  } catch (error) {
    console.error('Error fetching ticket status:', error);
    return { available: 0 };
  }
};
