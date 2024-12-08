// src/pages/ConfigurationPage.jsx
import React from 'react';
import ConfigurationForm from '../components/ConfigurationForm';
import './ConfigurationPage.css'; // Import the styles for the page

function ConfigurationPage() {
  const handleConfigurationSubmit = (config) => {
    console.log('Configuration submitted:', config);
  };

  return (
    <div className="configuration-page">
      <h1>Configuration Settings</h1>

      <div className="configuration-form-container">
        <ConfigurationForm onSubmit={handleConfigurationSubmit} />
      </div>
    </div>
  );
}

export default ConfigurationPage;
