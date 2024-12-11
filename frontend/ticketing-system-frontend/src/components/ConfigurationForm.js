import React, { useState, useEffect } from 'react';
import './configurationForm.css'

const ConfigurationForm = ({ onSubmit, selectedConfig, onGoToDashboard }) => {
  const [config, setConfig] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
    numberOfVendors: '',
    numberOfCustomers: '',
  });

  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (selectedConfig) {
      setConfig(selectedConfig);
    }
  }, [selectedConfig]);

  const handleChange = (e) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: '' });
  };

  const validateForm = () => {
    const newErrors = {};
    let isValid = true;

    Object.entries(config).forEach(([key, value]) => {
      if (value <= 0) {
        newErrors[key] = `${key.replace(/([A-Z])/g, ' $1')} must be greater than 0.`;
        isValid = false;
      }
    });

    setErrors(newErrors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) return;

    onSubmit(config);

    setConfig({
      totalTickets: '',
      ticketReleaseRate: '',
      customerRetrievalRate: '',
      maxTicketCapacity: '',
      numberOfVendors: '',
      numberOfCustomers: '',
    });
  };

  return (
    <div>
      <h2>{selectedConfig ? 'Update Configuration' : 'Create Configuration'}</h2>
      <form onSubmit={handleSubmit}>
        {Object.keys(config).map((field) => (
          <div key={field}>
            <label>
              {field.replace(/([A-Z])/g, ' $1').toUpperCase()}:
              <input
                type="number"
                name={field}
                value={config[field]}
                onChange={handleChange}
                required
              />
            </label>
            {errors[field] && <p style={{ color: 'red' }}>{errors[field]}</p>}
          </div>
        ))}
        <button type="submit">{selectedConfig ? 'Update Configuration' : 'Save Configuration'}</button>
      </form>

      {/* Go to Dashboard button */}
      <button onClick={onGoToDashboard} style={{ marginTop: '20px' }}>
        Go to Dashboard
      </button>
    </div>
  );
};

export default ConfigurationForm;
