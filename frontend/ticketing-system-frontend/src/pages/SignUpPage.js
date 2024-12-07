
import React from 'react';
import SignUpForm from '../components/SignUpForm.js';
import './SignUpPage.css';

function SignUpPage() {
  const handleSignUp = (email, password) => {
    console.log('User signed up:', email);
    // Implement registration logic here
  };

  return (
    <div className="signup-page">
      <h1>Sign Up</h1>
      <SignUpForm onSignUp={handleSignUp} />
    </div>
  );
}

export default SignUpPage;
