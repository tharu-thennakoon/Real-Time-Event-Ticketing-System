import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ConfigurationForm from '../components/ConfigurationForm';
import './configurationPage.css';

const ConfigurationPage = () => {
  const [configurations, setConfigurations] = useState([]);
  const [selectedConfig, setSelectedConfig] = useState(null);
  const navigate = useNavigate(); // React Router hook

  const handleCreateOrUpdate = (config) => {
    if (config.id) {
      // Update existing configuration
      setConfigurations(configurations.map((item) => (item.id === config.id ? config : item)));
    } else {
      // Create new configuration
      const newConfig = { id: Date.now(), ...config };
      setConfigurations([...configurations, newConfig]);
    }

    setSelectedConfig(null); // Clear the form after save
  };

  const handleEdit = (config) => {
    setSelectedConfig(config);
  };

  const handleDelete = (id) => {
    setConfigurations(configurations.filter((config) => config.id !== id));
  };

  const goToDashboard = () => {
    navigate('/dashboard'); // Navigate to dashboard
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Configuration Management</h1>
      <ConfigurationForm onSubmit={handleCreateOrUpdate} selectedConfig={selectedConfig} onGoToDashboard={goToDashboard} />
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
            <button onClick={() => handleDelete(config.id)} style={{ marginLeft: '10px' }}>
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ConfigurationPage;
