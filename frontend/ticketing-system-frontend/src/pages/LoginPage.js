
import React, { useState } from 'react';
import LoginForm from '../components/LoginForm';
import './LoginPage.css';

function LoginPage() {
  const [userType, setUserType] = useState('customer');

  const handleLogin = (email, password, userType) => {
    console.log(`Logged in as ${userType}`);
    // Implement authentication logic here
  };

  return (
    <div className="login-page">
      <h1>Login</h1>
      <LoginForm onLogin={handleLogin} />
    </div>
  );
}

export default LoginPage;
