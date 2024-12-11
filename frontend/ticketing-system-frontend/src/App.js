import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import axios from 'axios';
import Dashboard from './pages/Dashboard';
import ConfigurationPage from './pages/ConfigurationPage';

// Configure default Axios settings
axios.defaults.baseURL = 'http://localhost:8080/api/ticket-pool';
axios.defaults.headers.common['Content-Type'] = 'application/json';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ConfigurationPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Router>
  );
}

export default App;