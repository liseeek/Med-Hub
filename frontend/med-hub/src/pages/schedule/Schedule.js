import React, { useState, useEffect } from 'react';
import { Helmet } from "react-helmet";
import styles from './Schedule.module.css';
import NavRespo from '../../components/NavRespo';
import logo from '../../img/logo.svg';
import axios from "axios";
import { getAuthToken } from "../../helpers/axiosHelper";

const Schedule = () => {
  const [appointments, setAppointments] = useState([]);
  const [messages, setMessages] = useState('');

  const fetchAppointments = async () => {
    const token = getAuthToken();
    if (!token) {
      console.error('No token found');
      setMessages('You are not authenticated. Please log in.');
      return;
    }

    try {
      const response = await axios.get('/v1/appointments/user', {
        headers: { Authorization: `Bearer ${token}` },
      });
      setAppointments(response.data);
      // Reset messages if fetch is successful
      setMessages('');
    } catch (error) {
      console.error("Failed to fetch appointments:", error);
      setMessages("Failed to fetch appointments. Please try again later.");
    }
  };

  useEffect(() => {
    fetchAppointments();
  }, []);

  const handleCancel = async (appointmentId) => {
    const token = getAuthToken();
    if (!token) {
      console.error('No token found');
      setMessages('You are not authenticated. Please log in.');
      return;
    }

    try {
      await axios.delete(`/v1/appointments/${appointmentId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setMessages("Appointment canceled successfully.");
      // Refresh the list of appointments
      fetchAppointments(); // Ensure you have a method to refetch appointments
    } catch (error) {
      console.error("Failed to cancel appointment:", error.response?.data?.message || "Error occurred");
      setMessages("Failed to cancel the appointment. Please try again.");
    }
  };

  const formatDate = (dateArray) => {
    return `${dateArray[0]}-${String(dateArray[1]).padStart(2, '0')}-${String(dateArray[2]).padStart(2, '0')}`;
  };

  const formatTime = (timeArray) => {
    return `${String(timeArray[0]).padStart(2, '0')}:${String(timeArray[1]).padStart(2, '0')}`;
  };

  return (
      <div>
        <Helmet>
          <meta name="viewport" content="width=device-width, initial-scale=1" />
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
            {messages && <div className={styles.messages}>{messages}</div>}
            <h1 className={styles.scheduleVisitsHeader}>Your Visits</h1>
            {appointments.map((appointment) => (
                <div className={styles.scheduleVisitsContainer} key={appointment.appointmentId}>
                  <h1 className={styles.scheduleSpecializationHeader}>{appointment.doctor.specialization ? appointment.doctor.specialization.specializationName : 'Specialization not available'}</h1>
                  <div className={styles.scheduleDataContainer}>
                    <p className={styles.scheduleLeft}>Doctor: {appointment.doctor.name} {appointment.doctor.surname}</p>
                    <p className={styles.scheduleRight}>
                      Date: {formatDate(appointment.date)}
                      <br />
                      Time: {formatTime(appointment.time)}
                      <br />
                      Location: {appointment.location.locationName}, {appointment.location.address}, {appointment.location.city}, {appointment.location.country}
                    </p>
                  </div>
                  <div>
                    <button onClick={() => handleCancel(appointment.appointmentId)} className={styles.cancelButtonStyle}>CANCEL</button>
                  </div>
                </div>
            ))}
          </div>
        </main>
      </div>
  );
};

export default Schedule;
