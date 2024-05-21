import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import OSPage from './components/OSPage';
import Header from './components/Header';
import './App.css';

function App() {
  return (
    <div className="app">
      <Router>
        <Header />
        <div className="content">
          <Routes>
            <Route path="/" element={<LoginForm />} />
            <Route path="/os" element={<OSPage />} />
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
