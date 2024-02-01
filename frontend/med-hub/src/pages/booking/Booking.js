import React, { useState } from 'react';
import {Helmet} from "react-helmet";
import styles from './Booking.module.css';
import NavRespo from '../../components/NavRespo'; // Adjust this import to your actual navigation component
import logo from '../../img/logo.svg'; // Update the path to your logo image

const Booking = () => {
  const [formData, setFormData] = useState({
    specializations: '',
    doctor: '',
    date: '',
    time: '',
    // Add other form fields as needed
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Logic to handle form submission
    console.log(formData);
  };

  return (
    <div>
        <Helmet>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
        </Helmet>
        <div className={styles.bookingBaseContainer}>
            <header className={styles.bookingHeader}>
                <div className={styles.bookingLogo}>
                    <img src={logo} alt="Logo" />
                </div>
                <NavRespo /> 
            </header>
        </div>
      <main className={styles.bookingMain}>
        <div className={styles.bookingContainer}>
          <h1 className={styles.bookingHeader}>Make an appointment</h1>
            <form className={styles.bookingForm} onSubmit={handleSubmit}>
              <div className={styles.bookingMessages}>
                {/* Messages will be displayed here */}
              </div>
              <label htmlFor="specializations">Select Specialization:</label>
              <select
                name="specializations"
                id="specializations"
                value={formData.specializations}
                onChange={handleChange}
              >
              </select>
              <label htmlFor="doctor">Select Doctor:</label>
              <select
                name="doctor"
                id="doctor"
                value={formData.doctor}
                onChange={handleChange}
              >
                {/* Options for doctors will be dynamically populated */}
              </select>
              <label htmlFor="date">Select Date:</label>
              <select
                id="date"
                name="date"
                value={formData.date}
                onChange={handleChange}
                disabled
              >
                <option value="" selected disabled>Select a date</option>
                {/* Dynamically populate dates */}
              </select>
              <label htmlFor="time">Select Time:</label>
              <select
                id="time"
                name="time"
                value={formData.time}
                onChange={handleChange}
                disabled
              >
                <option value="" selected disabled>Select a time</option>
                {/* Dynamically populate times */}
              </select>
              <button className={styles.bookingButton} type="submit">BOOK</button>
            </form>
        </div>
      </main>
    </div>
  );
};

export default Booking;