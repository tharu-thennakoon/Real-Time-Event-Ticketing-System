// src/pages/TicketPage.jsx
import React from 'react';
import TicketStatus from '../components/TicketStatus';

function TicketPage() {
  return (
    <div className="ticket-page">
      <h1>Ticket Pool Status</h1>
      <TicketStatus />
    </div>
  );
}

export default TicketPage;
