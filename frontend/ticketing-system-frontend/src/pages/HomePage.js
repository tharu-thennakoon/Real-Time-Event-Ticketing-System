// src/pages/HomePage.js
import React from 'react';
import { Link } from 'react-router-dom';
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
      </div>
    </div>
  );
}

export default HomePage;
