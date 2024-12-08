// src/pages/LoginPage.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import LoginForm from '../components/LoginForm';
import './LoginPage.css';

function LoginPage() {
  const navigate = useNavigate(); // React Router hook for navigation

  const handleLogin = (email, password) => {
    console.log('User logged in:', email);
    // Simulate successful login logic
    // After successful login, navigate to Home Page
    navigate('/');
  };

  return (
    <div className="login-page">
      <h1>Log In</h1>
      <LoginForm onLogin={handleLogin} />
    </div>
  );
}

export default LoginPage;
