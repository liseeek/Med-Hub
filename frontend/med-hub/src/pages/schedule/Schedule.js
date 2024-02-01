import React, { useState, useEffect } from 'react';
import {Helmet} from "react-helmet";
import styles from './Schedule.module.css';
import NavRespo from '../../components/NavRespo'; // Adjust this import as necessary
import logo from '../../img/logo.svg'; // Update the path to your logo image

const Schedule = () => {
  const [appointments, setAppointments] = useState([]);
  const [messages, setMessages] = useState({
    success: '',
    error: ''
  });

  useEffect(() => {
    // Fetch appointments from your backend and set them in state
    // setAppointments(fetchedAppointments);
  }, []);

  const handleCancel = (appointmentId) => {
    // Implement cancellation logic here
    console.log("Cancel appointment ID:", appointmentId);
  };

  return (
    <div>
        <Helmet>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
        </Helmet>
    <div className={styles.scheduleBaseContainer}>
      <header className={styles.scheduleHeader}>
        <div className={styles.scheduleLogo}>
          <img src={logo} alt="Logo" />
        </div>
        <NavRespo />
      </header>
      </div>
      <main>
        <div className={styles.scheduleVisits}>
          {/* Display success or error messages */}
          <div className={styles.messages}>
            {messages.success && <div className={styles.successMessage}>{messages.success}</div>}
            {messages.error && <div className={styles.errorMessage}>{messages.error}</div>}
          </div>

          <h1 className={styles.scheduleVisitsHeader}>Your Visits</h1>
          {appointments.map((appointment) => (
            <div className={styles.scheduleVisitsContainer} key={appointment.id}>
              <h1 className={styles.specializationHeader}>{appointment.specializationName}</h1>
              <div className={styles.dataContainer}>
                <p className={styles.scheduleLeft}>Doctor: {appointment.doctorName}</p>
                <p className={styles.scheduleRight}>
                  Date: {appointment.appointmentDate}
                  <br />
                  Location: {appointment.address}, {appointment.city}, {appointment.country}
                </p>
              </div>
              <div className={styles.scheduleCancelButton}>
                <button onClick={() => handleCancel(appointment.id)} className={styles.cancelButtonStyle}>CANCEL</button>
              </div>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
};

export default Schedule;