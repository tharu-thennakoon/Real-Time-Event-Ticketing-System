import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ConfigurationForm from '../components/ConfigurationForm';

const ConfigurationPage = () => {
  const [configurations, setConfigurations] = useState([]);
  const [currentConfig, setCurrentConfig] = useState(null); // To store the current configuration
  const navigate = useNavigate();

  const handleCreate = (newConfig) => {
    const updatedConfig = { id: Date.now(), ...newConfig };
    setConfigurations([...configurations, updatedConfig]);
    setCurrentConfig(updatedConfig); // Save the latest configuration for passing to the dashboard
  };

  const goToDashboard = () => {
    if (!currentConfig) {
      alert('Please fill out the configuration form first.');
      return;
    }
    navigate('/dashboard', { state: { config: currentConfig } }); // Pass configuration to the dashboard
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Configuration Management</h1>
      <ConfigurationForm onSubmit={handleCreate} />
      <h2>Saved Configurations</h2>
      <ul>
        {configurations.map((config) => (
          <li key={config.id}>
            <p>
              <strong>ID:</strong> {config.id} <br />
              <strong>Total Tickets:</strong> {config.totalTickets} <br />
              <strong>Ticket Release Rate:</strong> {config.ticketReleaseRate} ms
            </p>
          </li>
        ))}
      </ul>
      <button
        style={{
          backgroundColor: '#007BFF',
          color: '#fff',
          padding: '10px 15px',
          border: 'none',
          borderRadius: '5px',
          cursor: 'pointer',
          marginTop: '20px',
        }}
        onClick={goToDashboard}
      >
        Go to Dashboard
      </button>
    </div>
  );
};

export default ConfigurationPage;
