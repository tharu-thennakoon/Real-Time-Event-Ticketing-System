// src/components/ConfigurationForm.jsx
import React, { useState } from 'react';
import './ConfigurationForm.css';

function ConfigurationForm({ onSubmit }) {
  const [totalTickets, setTotalTickets] = useState(0);
  const [ticketReleaseRate, setTicketReleaseRate] = useState(0);
  const [customerRetrievalRate, setCustomerRetrievalRate] = useState(0);
  const [maxTicketCapacity, setMaxTicketCapacity] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();

    // Pass configuration data to the parent component
    const config = {
      totalTickets,
      ticketReleaseRate,
      customerRetrievalRate,
      maxTicketCapacity,
    };
    onSubmit(config);
  };

  return (
    <form onSubmit={handleSubmit} className="config-form">
      <h2>Configuration Settings</h2>

      {/* Total Tickets */}
      <label>
        Total Tickets:
        <input
          type="number"
          value={totalTickets}
          onChange={(e) => setTotalTickets(e.target.value)}
          min="0"
          required
        />
      </label>

      {/* Ticket Release Rate */}
      <label>
        Ticket Release Rate:
        <input
          type="number"
          value={ticketReleaseRate}
          onChange={(e) => setTicketReleaseRate(e.target.value)}
          min="0"
          required
        />
      </label>

      {/* Customer Retrieval Rate */}
      <label>
        Customer Retrieval Rate:
        <input
          type="number"
          value={customerRetrievalRate}
          onChange={(e) => setCustomerRetrievalRate(e.target.value)}
          min="0"
          required
        />
      </label>

      {/* Maximum Ticket Capacity */}
      <label>
        Maximum Ticket Capacity:
        <input
          type="number"
          value={maxTicketCapacity}
          onChange={(e) => setMaxTicketCapacity(e.target.value)}
          min="0"
          required
        />
      </label>

      {/* Submit Button */}
      <button type="submit">Apply Settings</button>
    </form>
  );
}

export default ConfigurationForm;
