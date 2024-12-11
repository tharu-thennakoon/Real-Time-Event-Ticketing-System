// Dashboard.js
import React, { useState } from 'react';
import CustomerVsVendorChart from '../components/CustomerVsVendorChart';
import './Dashboard.css';

const Dashboard = () => {
  const [isRunning, setIsRunning] = useState(false);
  const [totalTickets, setTotalTickets] = useState(100);  // From configuration
  const [releasedTickets, setReleasedTickets] = useState(0);  // Tracks released tickets
  const [purchasedTickets, setPurchasedTickets] = useState(0);  // Tracks purchased tickets
  const [config, setConfig] = useState({
    totalTickets: 100,
    ticketReleaseRate: 500,
    customerRetrievalRate: 500,
    maxTicketCapacity: 1000,
    numberOfVendors: 3,
    numberOfCustomers: 5,
  });

  const handleStart = () => {
    setIsRunning(true);  // Start simulation
    // Start simulating ticket release and purchase
    simulateTickets();
  };

  const handleStop = () => setIsRunning(false);  // Stop simulation

  const simulateTickets = () => {
    const releaseInterval = setInterval(() => {
      if (isRunning) {
        setReleasedTickets((prev) => prev + 1);  // Increment released tickets
      } else {
        clearInterval(releaseInterval);
      }
    }, config.ticketReleaseRate);

    const purchaseInterval = setInterval(() => {
      if (isRunning) {
        setPurchasedTickets((prev) => prev + 1);  // Increment purchased tickets
      } else {
        clearInterval(purchaseInterval);
      }
    }, config.customerRetrievalRate);
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Ticket System Dashboard</h1>
      
      

      <div>
        <h2>Control Panel</h2>
        <button onClick={handleStart} style={{ marginRight: '10px' }}>Start</button>
        <button onClick={handleStop} style={{ marginRight: '10px' }}>Stop</button>
      </div>

      <CustomerVsVendorChart isRunning={isRunning} />  {/* The chart updates based on 'isRunning' */}

      <div>
        <h2>Simulation Details</h2>
        <p><strong>Total Tickets:</strong> {totalTickets}</p>
        <p><strong>Released Tickets:</strong> {releasedTickets}</p>
        <p><strong>Purchased Tickets:</strong> {purchasedTickets}</p>
      </div>
    </div>
  );
};

export default Dashboard;
