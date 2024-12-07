// src/pages/HomePage.js
import React, { useState } from 'react';
import LoginForm from '../components/LoginForm';
import SignUpForm from '../components/SignUpForm';
import TicketStatus from '../components/TicketStatus';
import ControlPanel from '../components/ControlPanel';
import './HomePage.css';
import LogDisplay from '../components/LogDisplay';

function HomePage() { // Correct function name matches file name
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Track login state
  const [showSignUp, setShowSignUp] = useState(false); // Toggle between Login and Signup forms

  // Handle login
  const handleLogin = (email, password) => {
    console.log('User logged in:', email);
    setIsLoggedIn(true); // Simulate successful login
  };

  // Handle signup
  const handleSignUp = (email, password) => {
    console.log('User signed up:', email);
    setIsLoggedIn(true); // Simulate successful signup
  };

  return (
    <div className="dashboard-page">
      <h1>Welcome to Real-Time Event Ticketing System</h1>

      {/* Show Login/Signup Forms if not logged in */}
      {!isLoggedIn ? (
        <div className="auth-section">
          {showSignUp ? (
            <>
              <SignUpForm onSignUp={handleSignUp} />
              <p>
                Already have an account?{' '}
                <button onClick={() => setShowSignUp(false)} className="link-button">
                  Login here
                </button>
              </p>
            </>
          ) : (
            <>
              <LoginForm onLogin={handleLogin} />
              <p>
                Don't have an account?{' '}
                <button onClick={() => setShowSignUp(true)} className="link-button">
                  Sign up here
                </button>
              </p>
            </>
          )}
        </div>
      ) : (
        // Show dashboard content if logged in
        <div className="dashboard-content">
          <TicketStatus />
          <ControlPanel />
        </div>
      )}

      <LogDisplay/>
    </div>
  );
}

export default HomePage; // Correct export matches component name
