import React, { useState, useEffect } from 'react';
import './LogDisplay.css';

const LogDisplay = () => {
    const [logs, setLogs] = useState([
        { timestamp: '2024-12-08 10:00', action: 'Ticket purchased by customer 1' },
        { timestamp: '2024-12-08 10:05', action: 'Ticket released by vendor 1' },
    ]);

    const addLog = (newLog) => {
        setLogs((prevLogs) => [...prevLogs, newLog]);
    };

    useEffect(() => {
        const interval = setInterval(() => {
            addLog({
                timestamp: new Date().toLocaleString(),
                action: `New log at ${new Date().toLocaleTimeString()}`,
            });
        }, 5000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div className="log-display">
            <h3>Log Display</h3>
            <table className="log-table">
                <thead>
                    <tr>
                        <th>Timestamp</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {logs.map((log, index) => (
                        <tr key={index}>
                            <td>{log.timestamp}</td>
                            <td>{log.action}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default LogDisplay;
