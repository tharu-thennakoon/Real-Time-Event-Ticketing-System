import React, { useState } from 'react';
import './configurationForm.css';

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
    setErrors({ ...errors, [e.target.name]: '' }); // Reset error when user starts typing
  };

  const validateForm = () => {
    const newErrors = {};
    let isValid = true;

    // Validate each field
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

    setErrors(newErrors); // Set error messages
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Validate the form before submitting
    if (!validateForm()) {
      return; // Don't submit if the form is invalid
    }

    // If valid, submit the form data
    onSubmit(config);

    // Reset form after submission
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
        <div>
          <label>
            Total Tickets:
            <input
              type="number"
              name="totalTickets"
              value={config.totalTickets}
              onChange={handleChange}
              required
            />
          </label>
          {errors.totalTickets && <p style={{ color: 'red' }}>{errors.totalTickets}</p>}
        </div>
        <br />
        <div>
          <label>
            Ticket Release Rate (ms):
            <input
              type="number"
              name="ticketReleaseRate"
              value={config.ticketReleaseRate}
              onChange={handleChange}
              required
            />
          </label>
          {errors.ticketReleaseRate && <p style={{ color: 'red' }}>{errors.ticketReleaseRate}</p>}
        </div>
        <br />
        <div>
          <label>
            Customer Retrieval Rate (ms):
            <input
              type="number"
              name="customerRetrievalRate"
              value={config.customerRetrievalRate}
              onChange={handleChange}
              required
            />
          </label>
          {errors.customerRetrievalRate && <p style={{ color: 'red' }}>{errors.customerRetrievalRate}</p>}
        </div>
        <br />
        <div>
          <label>
            Max Ticket Capacity:
            <input
              type="number"
              name="maxTicketCapacity"
              value={config.maxTicketCapacity}
              onChange={handleChange}
              required
            />
          </label>
          {errors.maxTicketCapacity && <p style={{ color: 'red' }}>{errors.maxTicketCapacity}</p>}
        </div>
        <br />
        <div>
          <label>
            Number of Vendors:
            <input
              type="number"
              name="numberOfVendors"
              value={config.numberOfVendors}
              onChange={handleChange}
              required
            />
          </label>
          {errors.numberOfVendors && <p style={{ color: 'red' }}>{errors.numberOfVendors}</p>}
        </div>
        <br />
        <div>
          <label>
            Number of Customers:
            <input
              type="number"
              name="numberOfCustomers"
              value={config.numberOfCustomers}
              onChange={handleChange}
              required
            />
          </label>
          {errors.numberOfCustomers && <p style={{ color: 'red' }}>{errors.numberOfCustomers}</p>}
        </div>
        <br />
        <button type="submit">Save Configuration</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;
