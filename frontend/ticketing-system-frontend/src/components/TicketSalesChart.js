import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const TicketSalesChart = () => {
  const [salesData, setSalesData] = useState({
    labels: [], // Time intervals
    datasets: [
      {
        label: 'Tickets Sold Over Time',
        data: [], // Number of tickets sold
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
      },
    ],
  });

  // Simulate data updates
  useEffect(() => {
    const interval = setInterval(() => {
      setSalesData((prevData) => {
        const newTime = prevData.labels.length + 1; // Mock time intervals
        const newTickets = Math.floor(Math.random() * 10) + 1; // Mock tickets sold
        return {
          ...prevData,
          labels: [...prevData.labels, `Time ${newTime}`],
          datasets: [
            {
              ...prevData.datasets[0],
              data: [...prevData.datasets[0].data, newTickets],
            },
          ],
        };
      });
    }, 2000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h2>Ticket Sales Over Time</h2>
      <Line data={salesData} />
    </div>
  );
};

export default TicketSalesChart;
