// src/components/ControlPanel.js
import React from 'react';
import './controlPanel.css';

function ControlPanel() {
  const startSystem = () => {
    console.log('System Started');
  };

  const stopSystem = () => {
    console.log('System Stopped');
  };

  const resetSystem = () => {
    console.log('System Reset');
  };

  return (
    <div className="control-panel">
      <button onClick={startSystem}>Start</button>
      <button onClick={stopSystem}>Stop</button>
      <button onClick={resetSystem}>Reset</button>
    </div>
  );
}

export default ControlPanel;
