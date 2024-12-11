import React from 'react';

const LogDisplay = ({ logs }) => {
  return (
    <div>
      <h2>Activity Log</h2>
      <ul>
        {logs.map((log, index) => (
          <li key={index}>{log}</li>
        ))}
      </ul>
    </div>
  );
};

export default LogDisplay;
