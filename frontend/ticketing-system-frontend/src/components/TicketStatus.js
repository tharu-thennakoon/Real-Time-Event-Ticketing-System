import React from 'react';

const TicketStatus = ({ config }) => {
  return (
    <div>
      <h2>Ticket Pool Status</h2>
      <p><strong>Total Tickets:</strong> {config.totalTickets || 'N/A'}</p>
      <p><strong>Current Pool Size:</strong> {config.maxTicketCapacity || 'N/A'}</p>
      <p><strong>Number of Vendors:</strong> {config.numberOfVendors || 'N/A'}</p>
      <p><strong>Number of Customers:</strong> {config.numberOfCustomers || 'N/A'}</p>
    </div>
  );
};

export default TicketStatus;
