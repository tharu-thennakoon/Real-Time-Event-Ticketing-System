import React, { useState } from 'react';

const LogDisplay = () => {
  const [logs] = useState([
    'Simulation started.',
    'Vendor-1 added Ticket ID: 1.',
    'Customer-2 retrieved Ticket ID: 1.',
    'Simulation stopped.',
  ]);

  return (
    <div>
      <h2>Log Display</h2>
      <ul>
        {logs.map((log, index) => (
          <li key={index}>{log}</li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;
