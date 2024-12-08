// src/pages/SignUpPage.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import SignUpForm from '../components/SignUpForm';
import './SignUpPage.css';

function SignUpPage() {
  const navigate = useNavigate(); // React Router hook for navigation

  const handleSignUp = (email, password) => {
    console.log('User signed up:', email);
    // Simulate successful signup logic
    // After successful signup, navigate to Home Page
    navigate('/');
  };

  return (
    <div className="signup-page">
      <h1>Sign Up</h1>
      <SignUpForm onSignUp={handleSignUp} />
    </div>
  );
}

export default SignUpPage;
