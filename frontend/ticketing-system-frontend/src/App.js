// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import ConfigurationPage from './pages/ConfigurationPage';
import TicketPage from './pages/TicketPage';
import ControlPage from './pages/ControlPage';
import LogPage from './pages/LogPage';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/configuration" element={<ConfigurationPage />} />
        <Route path="/ticket-status" element={<TicketPage />} />
        <Route path="/control-panel" element={<ControlPage />} />
        <Route path="/logs" element={<LogPage />} />
      </Routes>
    </Router>
  );
}

export default App;
