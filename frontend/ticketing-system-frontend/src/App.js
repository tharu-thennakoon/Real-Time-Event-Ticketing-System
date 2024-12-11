// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import ConfigurationPage from './pages/ConfigurationPage';

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
