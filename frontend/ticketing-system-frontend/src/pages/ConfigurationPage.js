import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import ConfigurationForm from '../components/ConfigurationForm';

const API_BASE_URL = 'http://localhost:8080/api/ticket-pool';

const ConfigurationPage = () => {
  const [configurations, setConfigurations] = useState([]);
  const [selectedConfig, setSelectedConfig] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch configurations on component mount
    const fetchConfigurations = async () => {
      try {
        const response = await axios.get(API_BASE_URL + '/configuration');
        setConfigurations([response.data]);
      } catch (error) {
        console.error('Error fetching configurations:', error);
      }
    };

    fetchConfigurations();
  }, []);

  const handleCreateOrUpdate = async (config) => {
    try {
      if (config.id) {
        // Update existing configuration
        const response = await axios.put(`${API_BASE_URL}/configuration/${config.id}`, config);
        setConfigurations(configurations.map((item) => 
          item.id === config.id ? response.data : item
        ));
      } else {
        // Create new configuration
        const response = await axios.post(API_BASE_URL + '/configuration', config);
        setConfigurations([...configurations, response.data]);
      }
      
      setSelectedConfig(null);
    } catch (error) {
      console.error('Error saving configuration:', error);
    }
  };

  const handleEdit = (config) => {
    setSelectedConfig(config);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${API_BASE_URL}/configuration/${id}`);
      setConfigurations(configurations.filter((config) => config.id !== id));
      setSelectedConfig(null);
    } catch (error) {
      console.error('Error deleting configuration:', error);
    }
  };

  const goToDashboard = async () => {
    try {
      // Fetch the last configuration to pass to dashboard
      const response = await axios.get(API_BASE_URL + '/configuration');
      navigate('/dashboard', { state: { config: response.data } });
    } catch (error) {
      console.error('Error fetching configuration:', error);
      navigate('/dashboard');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Configuration Management</h1>
      <ConfigurationForm 
        onSubmit={handleCreateOrUpdate} 
        selectedConfig={selectedConfig} 
        onDelete={handleDelete} 
      />
      <h2>Saved Configurations</h2>
      <ul>
        {configurations.map((config) => (
          <li key={config.id}>
            <p>
              <strong>ID:</strong> {config.id} <br />
              <strong>Total Tickets:</strong> {config.totalTickets} <br />
              <strong>Ticket Release Rate:</strong> {config.ticketReleaseRate} ms
            </p>
            <button onClick={() => handleEdit(config)}>Edit</button>
          </li>
        ))}
      </ul>
      <button onClick={goToDashboard}>Go to Dashboard</button>
    </div>
  );
};

export default ConfigurationPage;