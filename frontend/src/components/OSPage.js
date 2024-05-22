import React from 'react';
import Sidebar from './Sidebar';
import './OSPage.css';

function OSPage() {
  return (
    <div className="os-page">
      <Sidebar />
      <div className="content">
        <h1>Welcome to the OS Page!</h1>
      </div>
    </div>
  );
}

export default OSPage;
