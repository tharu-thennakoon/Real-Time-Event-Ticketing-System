import React from 'react';

const ControlPanel = () => {
  const handleStart = () => {
    alert('Simulation Started!');
  };

  const handleStop = () => {
    alert('Simulation Stopped!');
  };

  const handleReset = () => {
    alert('Simulation Reset!');
  };

  return (
    <div>
      <h2>Control Panel</h2>
      <button onClick={handleStart} style={{ marginRight: '10px' }}>Start</button>
      <button onClick={handleStop} style={{ marginRight: '10px' }}>Stop</button>
      <button onClick={handleReset}>Reset</button>
    </div>
  );
};

export default ControlPanel;
