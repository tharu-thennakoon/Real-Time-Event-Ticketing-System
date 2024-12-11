import React, { useState } from 'react';
import ConfigurationForm from '../components/ConfigurationForm';

const ConfigurationPage = () => {
  const [configurations, setConfigurations] = useState([]);

  const handleCreate = (newConfig) => {
    setConfigurations([...configurations, { id: Date.now(), ...newConfig }]);
  };

  const handleDelete = (id) => {
    setConfigurations(configurations.filter((config) => config.id !== id));
  };

  const handleUpdate = (updatedConfig) => {
    setConfigurations(configurations.map((config) =>
      config.id === updatedConfig.id ? updatedConfig : config
    ));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Configuration Management</h1>
      <ConfigurationForm onSubmit={handleCreate} />
      <h2>Configurations</h2>
      <ul>
        {configurations.map((config) => (
          <li key={config.id}>
            <p>
              <strong>ID:</strong> {config.id} <br />
              <strong>Total Tickets:</strong> {config.totalTickets} <br />
              <strong>Ticket Release Rate:</strong> {config.ticketReleaseRate} ms
            </p>
            <button onClick={() => handleDelete(config.id)}>Delete</button>
            <button
              onClick={() =>
                handleUpdate({ ...config, totalTickets: config.totalTickets + 10 })
              }
            >
              Update (+10 Tickets)
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ConfigurationPage;
