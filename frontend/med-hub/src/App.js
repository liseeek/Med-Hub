import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/register/Register';
import Login from './pages/login/Login';
import MainPage from './pages/mainpage/MainPage';
import Adding from './pages/adding/Adding';
import Booking from './pages/booking/Booking';
import Schedule from './pages/schedule/Schedule';
import './global.css'

function App() {
  return (
  <Router>
    <div className="App">
      <Routes>
        <Route path="/" element={<Login />}/>
        <Route path="/register" element={<Register />} />
        <Route path="/mainpage" element={<MainPage />} />
        <Route path="/adding" element={<Adding />} />
        <Route path="/booking" element={<Booking />} />
        <Route path="/schedule" element={<Schedule />} />
      </Routes>
      
    </div>
  </Router>
    
  );
}

export default App;
