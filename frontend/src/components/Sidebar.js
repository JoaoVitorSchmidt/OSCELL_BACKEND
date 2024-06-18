import React from 'react';
import './Sidebar.css';
import { FaMobileAlt } from 'react-icons/fa';

function Sidebar() {
  return (
    <div className="sidebar">
      <div className="logo">
        <FaMobileAlt size={32} color="white" />
        <span>OS CELL</span>
      </div>
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">OS's</a></li>
      </ul>
    </div>
  );
}

export default Sidebar;
