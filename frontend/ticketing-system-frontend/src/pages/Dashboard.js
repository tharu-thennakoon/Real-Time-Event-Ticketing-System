import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import CustomerVsVendorChart from '../components/CustomerVsVendorChart';
import LogDisplay from '../components/LogDisplay';

const API_BASE_URL = 'http://localhost:8080/api/ticket-pool';

const Dashboard = () => {
  const location = useLocation();
  const config = location.state?.config || {}; 
  const [isRunning, setIsRunning] = useState(false);
  const [logs, setLogs] = useState([]);
  const [analytics, setAnalytics] = useState(null);

  const handleStart = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/start-simulation`);
      setIsRunning(true);
      setLogs([...logs, response.data]);
    } catch (error) {
      console.error('Error starting simulation:', error);
    }
  };

  const handleStop = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/stop-simulation`);
      setIsRunning(false);
      setLogs([...logs, response.data]);
    } catch (error) {
      console.error('Error stopping simulation:', error);
    }
  };

  useEffect(() => {
    let intervalId;
    const fetchLogs = async () => {
      try {
        const logsResponse = await axios.get(`${API_BASE_URL}/logs`);
        setLogs(logsResponse.data.split('\n'));
        
        const analyticsResponse = await axios.get(`${API_BASE_URL}/analytics`);
        setAnalytics(analyticsResponse.data);
      } catch (error) {
        console.error('Error fetching logs or analytics:', error);
      }
    };

    if (isRunning) {
      intervalId = setInterval(fetchLogs, 2000);
    }

    return () => {
      if (intervalId) clearInterval(intervalId);
    };
  }, [isRunning]);

  return (
    <div style={{ padding: '20px' }}>
      <h1>Ticket System Dashboard</h1>
      <div>
        <h2>Configuration</h2>
        <p><strong>Total Tickets:</strong> {config.totalTickets}</p>
        <p><strong>Ticket Release Rate:</strong> {config.ticketReleaseRate} ms</p>
        <p><strong>Customer Retrieval Rate:</strong> {config.customerRetrievalRate} ms</p>
        <p><strong>Max Ticket Capacity:</strong> {config.maxTicketCapacity}</p>
        <p><strong>Number of Vendors:</strong> {config.numberOfVendors}</p>
        <p><strong>Number of Customers:</strong> {config.numberOfCustomers}</p>
      </div>
      
      {analytics && (
        <div>
          <h2>Analytics</h2>
          <pre>{analytics}</pre>
        </div>
      )}

      <div>
        <h2>Control Panel</h2>
        <button onClick={handleStart} style={{ marginRight: '10px' }}>Start</button>
        <button onClick={handleStop}>Stop</button>
      </div>

      <CustomerVsVendorChart isRunning={isRunning} />
      <LogDisplay logs={logs} />
    </div>
  );
};

export default Dashboard;