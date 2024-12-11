import React from 'react';
import TicketStatus from '../components/TicketStatus';
import ControlPanel from '../components/ControlPanel';
import LogDisplay from '../components/LogDisplay';
import TicketSalesChart from '../components/CustomerVsVendorChart';

const Dashboard = () => {
  return (
    <div style={{ padding: '20px' }}>
      <h1>Ticket System Dashboard</h1>
      <TicketStatus />
      <ControlPanel />
      <TicketSalesChart />
      <LogDisplay />
    </div>
  );
};

export default Dashboard;
