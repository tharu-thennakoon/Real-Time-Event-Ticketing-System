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

  const handleChange = (e) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
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
    <form onSubmit={handleSubmit}>
      <label>
        Total Tickets:
        <input type="number" name="totalTickets" value={config.totalTickets} onChange={handleChange} required />
      </label>
      <br />
      <label>
        Ticket Release Rate (ms):
        <input type="number" name="ticketReleaseRate" value={config.ticketReleaseRate} onChange={handleChange} required />
      </label>
      <br />
      <label>
        Customer Retrieval Rate (ms):
        <input type="number" name="customerRetrievalRate" value={config.customerRetrievalRate} onChange={handleChange} required />
      </label>
      <br />
      <label>
        Max Ticket Capacity:
        <input type="number" name="maxTicketCapacity" value={config.maxTicketCapacity} onChange={handleChange} required />
      </label>
      <br />
      <label>
        Number of Vendors:
        <input type="number" name="numberOfVendors" value={config.numberOfVendors} onChange={handleChange} required />
      </label>
      <br />
      <label>
        Number of Customers:
        <input type="number" name="numberOfCustomers" value={config.numberOfCustomers} onChange={handleChange} required />
      </label>
      <br />
      <button type="submit">Save Configuration</button>
    </form>
  );
};

export default ConfigurationForm;
