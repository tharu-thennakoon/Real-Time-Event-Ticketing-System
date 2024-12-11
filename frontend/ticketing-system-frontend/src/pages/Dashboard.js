import React from 'react';
import { useLocation } from 'react-router-dom';
import TicketStatus from '../components/TicketStatus';
import ControlPanel from '../components/ControlPanel';
import LogDisplay from '../components/LogDisplay';
import TicketSalesChart from '../components/CustomerVsVendorChart';

const Dashboard = () => {
  const location = useLocation();
  const config = location.state?.config || {};

  return (
    <div style={{ padding: '20px' }}>
      <h1>Ticket System Dashboard</h1>
      <TicketStatus config={config} /> {/* Pass configuration to TicketStatus */}
      <ControlPanel />
      <TicketSalesChart />
      <LogDisplay />
    </div>
  );
};

export default Dashboard;
