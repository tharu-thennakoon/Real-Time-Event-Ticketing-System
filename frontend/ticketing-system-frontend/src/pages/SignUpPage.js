// src/pages/SignUpPage.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LoginForm from '../components/LoginForm';
import SignUpForm from '../components/SignUpForm';
import './SignUpPage.css';

function SignUpPage() {
  const [showSignUp, setShowSignUp] = useState(true); // Initially show SignUp form
  const navigate = useNavigate(); // Navigation hook for redirection

  const handleLogin = (email, password) => {
    console.log('User logged in:', email);
    // Simulate successful login logic
    navigate('/'); // Redirect to Home Page
  };

  const handleSignUp = (email, password) => {
    console.log('User signed up:', email);
    // Simulate successful signup logic
    navigate('/'); // Redirect to Home Page
  };

  return (
    <div className="signup-page">
      <h1>{showSignUp ? 'Sign Up' : 'Log In'}</h1>

      {/* Toggle between SignUp and Login Forms */}
      {showSignUp ? (
        <>
          <SignUpForm onSignUp={handleSignUp} />
          <p>
            Already have an account?{' '}
            <button onClick={() => setShowSignUp(false)} className="link-button">
              Log in here
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
  );
}

export default SignUpPage;
