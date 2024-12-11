// ConfigurationForm.js
import React, { useState } from 'react';

const ConfigurationForm = ({ onSubmit }) => {
  const [config, setConfig] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
    numberOfVendors: '',
    numberOfCustomers: '',
  });

  const [errors, setErrors] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
    numberOfVendors: '',
    numberOfCustomers: '',
  });

  const handleChange = (e) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: '' });
  };

  const validateForm = () => {
    const newErrors = {};
    let isValid = true;

    if (config.totalTickets <= 0) {
      newErrors.totalTickets = 'Total Tickets must be greater than 0.';
      isValid = false;
    }
    if (config.ticketReleaseRate <= 0) {
      newErrors.ticketReleaseRate = 'Ticket Release Rate must be greater than 0.';
      isValid = false;
    }
    if (config.customerRetrievalRate <= 0) {
      newErrors.customerRetrievalRate = 'Customer Retrieval Rate must be greater than 0.';
      isValid = false;
    }
    if (config.maxTicketCapacity <= 0) {
      newErrors.maxTicketCapacity = 'Max Ticket Capacity must be greater than 0.';
      isValid = false;
    }
    if (config.numberOfVendors <= 0) {
      newErrors.numberOfVendors = 'Number of Vendors must be greater than 0.';
      isValid = false;
    }
    if (config.numberOfCustomers <= 0) {
      newErrors.numberOfCustomers = 'Number of Customers must be greater than 0.';
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

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
      <h2>Configuration Form</h2>
      <form onSubmit={handleSubmit}>
        {['totalTickets', 'ticketReleaseRate', 'customerRetrievalRate', 'maxTicketCapacity', 'numberOfVendors', 'numberOfCustomers'].map(field => (
          <div key={field}>
            <label>{field.replace(/([A-Z])/g, ' $1').toUpperCase()}:</label>
            <input
              type="number"
              name={field}
              value={config[field]}
              onChange={handleChange}
              required
            />
            {errors[field] && <p style={{ color: 'red' }}>{errors[field]}</p>}
          </div>
        ))}
        <button type="submit">Save Configuration</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;
