// src/pages/HomePage.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import ControlPanel from '../components/ControlPanel';
import './HomePage.css';

function HomePage() {
  return (
    <div className="home-page">
      {/* Navigation Bar */}
      <nav className="navbar">
        <h1>Event Ticketing System</h1>
        <div className="navbar-buttons">
          <Link to="/login" className="nav-button">
            Log In
          </Link>
          <Link to="/signup" className="nav-button">
            Sign Up
          </Link>
        </div>
      </nav>

      {/* Page Heading */}
      <div className="content">
        <h1>Welcome to Real-Time Event Ticketing System</h1>
        <p>Your one-stop solution for managing ticketing events effortlessly!</p>

        {/* Links for Components */}
        <div className="links">
          <Link to="/configuration" className="link-button">
            Configuration Settings
          </Link>

          {/* Control Panel Buttons */}
          <div className="control-panel-wrapper">
            <ControlPanel />
          </div>

          <Link to="/ticket-status" className="link-button">
            Ticket Pool Status
          </Link>
          <Link to="/logs" className="link-button">
            Log Display
          </Link>
        </div>
      </div>
    </div>
  );
}

export default HomePage;
