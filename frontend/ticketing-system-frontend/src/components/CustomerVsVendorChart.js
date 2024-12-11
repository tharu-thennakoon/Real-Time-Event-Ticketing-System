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

const CustomerVsVendorChart = () => {
  const [chartData, setChartData] = useState({
    labels: [], // Time intervals (e.g., T1, T2)
    datasets: [
      {
        label: 'Tickets Issued by Vendors',
        data: [], // Number of tickets issued by vendors
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        tension: 0.4, // Smooth line
        fill: true, // Fill area under the line
      },
      {
        label: 'Tickets Retrieved by Customers',
        data: [], // Number of tickets retrieved by customers
        borderColor: 'rgba(255, 99, 132, 1)',
        backgroundColor: 'rgba(255, 99, 132, 0.2)',
        tension: 0.4, // Smooth line
        fill: true, // Fill area under the line
      },
    ],
  });

  // Simulate real-time updates for customer vs vendor data
  useEffect(() => {
    const interval = setInterval(() => {
      setChartData((prevData) => {
        const currentTime = prevData.labels.length + 1; // Mock time intervals (e.g., seconds)
        const ticketsIssued = Math.floor(Math.random() * 10); // Mock vendor ticket issuance
        const ticketsRetrieved = Math.floor(Math.random() * 8); // Mock customer ticket retrieval

        return {
          ...prevData,
          labels: [...prevData.labels, `T${currentTime}`], // Append new time
          datasets: [
            {
              ...prevData.datasets[0],
              data: [...prevData.datasets[0].data, ticketsIssued], // Append vendor data
            },
            {
              ...prevData.datasets[1],
              data: [...prevData.datasets[1].data, ticketsRetrieved], // Append customer data
            },
          ],
        };
      });
    }, 1000); // Update every 1 second

    return () => clearInterval(interval); // Cleanup interval on component unmount
  }, []);

  return (
    <div>
      <h2>Customer vs Vendor Activity Over Time</h2>
      <Line
        data={chartData}
        options={{
          responsive: true,
          plugins: {
            legend: {
              position: 'top',
            },
            title: {
              display: true,
              text: 'Customer vs Vendor Ticket Activity',
            },
          },
          scales: {
            x: {
              title: {
                display: true,
                text: 'Time Intervals',
              },
            },
            y: {
              beginAtZero: true,
              title: {
                display: true,
                text: 'Number of Tickets',
              },
            },
          },
        }}
      />
    </div>
  );
};

export default CustomerVsVendorChart;
