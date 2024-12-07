// src/components/SignUpForm.js
import React, { useState } from 'react';
import './SignUpForm.css';

function SignUpForm({ onSignUp }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Basic validation
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }

    // Reset error and trigger signup action
    setError('');
    onSignUp(email, password); // Pass data to the parent component or API call
  };

  return (
    <form onSubmit={handleSubmit} className="signup-form">
      <h2>Create an Account</h2>

      {/* Email Field */}
      <input
        type="email"
        placeholder="Enter your email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="form-input"
        required
      />

      {/* Password Field */}
      <input
        type="password"
        placeholder="Enter your password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className="form-input"
        required
      />

      {/* Confirm Password Field */}
      <input
        type="password"
        placeholder="Confirm your password"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
        className="form-input"
        required
      />

      {/* Error Message */}
      {error && <p className="error-message">{error}</p>}

      {/* Submit Button */}
      <button type="submit" className="form-button">
        Sign Up
      </button>
    </form>
  );
}

export default SignUpForm;
