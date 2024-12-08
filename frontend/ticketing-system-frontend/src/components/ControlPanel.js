// src/components/ControlPanel.jsx
import React from 'react';
import './ControlPanel.css';

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
      <button onClick={startSystem} className="control-button start-button">
        Start
      </button>
      <button onClick={stopSystem} className="control-button stop-button">
        Stop
      </button>
      <button onClick={resetSystem} className="control-button reset-button">
        Reset
      </button>
    </div>
  );
}

export default ControlPanel;
