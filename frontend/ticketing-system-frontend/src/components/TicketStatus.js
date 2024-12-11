import React, { useState } from 'react';

const TicketStatus = () => {
  const [analytics, setAnalytics] = useState({
    totalTicketsIssued: 300,
    currentPoolSize: 50,
    maxPoolCapacity: 1000,
  });

  return (
    <div>
      <h2>Ticket Pool Status</h2>
      <p><strong>Total Tickets Issued:</strong> {analytics.totalTicketsIssued}</p>
      <p><strong>Current Pool Size:</strong> {analytics.currentPoolSize}</p>
      <p><strong>Max Pool Capacity:</strong> {analytics.maxPoolCapacity}</p>
    </div>
  );
};

export default TicketStatus;
