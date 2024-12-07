// src/components/TicketStatus.js
import React, { useState, useEffect } from 'react';
import { getTicketStatus } from '../services/ticketService';
import './TicketStatus.css';

function TicketStatus() {
  const [tickets, setTickets] = useState(0);

  useEffect(() => {
    const fetchTicketStatus = async () => {
      const status = await getTicketStatus();
      setTickets(status.available);
    };

    fetchTicketStatus();
  }, []);

  return (
    <div className="ticket-status">
      <h3>Ticket Pool Status</h3>
      <p>{tickets} tickets available</p>
    </div>
  );
}

export default TicketStatus;
