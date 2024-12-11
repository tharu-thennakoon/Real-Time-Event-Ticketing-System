import React, { useState, useEffect } from 'react';
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

const CustomerVsVendorChart = ({ isRunning }) => {
  const [chartData, setChartData] = useState({
    labels: [],
    datasets: [
      {
        label: 'Tickets Issued by Vendors',
        data: [],
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        tension: 0.4,
        fill: true,
      },
      {
        label: 'Tickets Retrieved by Customers',
        data: [],
        borderColor: 'rgba(255, 99, 132, 1)',
        backgroundColor: 'rgba(255, 99, 132, 0.2)',
        tension: 0.4,
        fill: true,
      },
    ],
  });

  useEffect(() => {
    let interval;
    if (isRunning) {
      interval = setInterval(() => {
        setChartData((prevData) => {
          const currentTime = prevData.labels.length + 1;
          const ticketsIssued = Math.floor(Math.random() * 10);
          const ticketsRetrieved = Math.floor(Math.random() * 8);

          return {
            ...prevData,
            labels: [...prevData.labels, `T${currentTime}`],
            datasets: [
              {
                ...prevData.datasets[0],
                data: [...prevData.datasets[0].data, ticketsIssued],
              },
              {
                ...prevData.datasets[1],
                data: [...prevData.datasets[1].data, ticketsRetrieved],
              },
            ],
          };
        });
      }, 1000); // Update every 1 second
    } else {
      clearInterval(interval); // Stop updates when simulation is stopped
    }

    return () => clearInterval(interval); // Cleanup interval on component unmount or stop
  }, [isRunning]);

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
