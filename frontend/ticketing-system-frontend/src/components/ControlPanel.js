import React from 'react';

const ControlPanel = ({ onStart, onStop, onReset }) => {
  return (
    <div>
      <h2>Control Panel</h2>
      <button onClick={onStart} style={{ marginRight: '10px' }}>Start</button>
      <button onClick={onStop} style={{ marginRight: '10px' }}>Stop</button>
      <button onClick={onReset}>Reset</button>
    </div>
  );
};

export default ControlPanel;
