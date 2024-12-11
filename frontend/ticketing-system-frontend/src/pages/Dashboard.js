import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import TicketStatus from '../components/TicketStatus';
import ControlPanel from '../components/ControlPanel';
import LogDisplay from '../components/LogDisplay';
import TicketSalesChart from '../components/CustomerVsVendorChart';

const Dashboard = () => {
  const location = useLocation();
  const config = location.state?.config || {};

  const [isRunning, setIsRunning] = useState(false);

  const handleStart = () => {
    setIsRunning(true);  // Start the simulation and trigger the chart update
  };

  const handleStop = () => {
    setIsRunning(false);  // Stop the simulation and stop the chart update
  };

  const handleReset = () => {
    setIsRunning(false);  // Stop updates
    // Reset other necessary data here, e.g., reset chart data if needed
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Ticket System Dashboard</h1>
      <TicketStatus config={config} />
      <ControlPanel onStart={handleStart} onStop={handleStop} onReset={handleReset} />
      <TicketSalesChart isRunning={isRunning} />  {/* Pass isRunning state */}
      <LogDisplay />
    </div>
  );
};

export default Dashboard;
