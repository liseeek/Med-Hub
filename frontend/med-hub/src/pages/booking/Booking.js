import React, {useEffect, useState} from 'react';
import {Helmet} from "react-helmet";
import ReactDatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import styles from './Booking.module.css';
import NavRespo from '../../components/NavRespo';
import logo from '../../img/logo.svg';
import axios from "axios";
import { jwtDecode } from 'jwt-decode';
import {getAuthToken} from "../../helpers/axios_helper";

const Booking = () => {
    const [formData, setFormData] = useState({
        specialization: '',
        doctor: '',
        date: '',
        time: '',
    });

    const [specializations, setSpecializations] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [startDate, setStartDate] = useState(new Date());
    const [messages, setMessages] = useState('');

    const isWeekday = (date) => {
        const day = date.getDay();
        return day !== 0 && day !== 6;
    };

    const handleChangeDate = (date) => {
        setStartDate(date);
        const formattedDate = formatDate(date);
        setFormData({ ...formData, date: formattedDate });
    };

    const formatDate = (date) => {
        const d = new Date(date);
        let month = '' + (d.getMonth() + 1);
        let day = '' + d.getDate();
        const year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    };

    useEffect(() => {
        const fetchSpecializations = async () => {
            try {
                const { data } = await axios.get('http://localhost:8080/v1/specializations');
                setSpecializations(data);
            } catch (error) {
                console.error('Failed to fetch specializations', error);
            }
        };
        fetchSpecializations();
    }, []);

    useEffect(() => {
        const fetchDoctors = async () => {
            if (formData.specialization) {
                try {
                    const { data } = await axios.get(`http://localhost:8080/v1/doctors/bySpecialization/${formData.specialization}`);
                    setDoctors(data);
                } catch (error) {
                    console.error('Failed to fetch doctors', error);
                }
            }
        };

        if (formData.specialization) {
            fetchDoctors();
        } else {
            setDoctors([]);
        }
    }, [formData.specialization]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = getAuthToken();
        console.log('Token',token);
        if (!token || typeof token !== 'string') {
            console.error('Invalid or missing token');
            setMessages('You are not authenticated. Please log in.');
            return;
        }

        console.log('formData',formData);
        const decodedToken = jwtDecode(token);
        const userId = decodedToken.userId;
        console.log('decodedToken',decodedToken);
        console.log('userId',userId);
        const appointmentData = {
            userId,
            doctorId: formData.doctor,
            date: formData.date,
            time: formData.time,
        };

        try {
            const response = await axios.post('/v1/appointments', appointmentData, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
            });

            if (response.status === 201) {
                setMessages('Appointment created successfully.');
            } else {
                setMessages('Failed to create an appointment. Please try again.');
            }
        } catch (error) {
            console.error('Failed to create an appointment', error);
            setMessages('Failed to create an appointment. Please check your inputs and try again.');
        }
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
                {messages && <div className={styles.bookingMessages}>{messages}</div>}
                <label htmlFor="specialization">Select Specialization:</label>
                <select
                    name="specialization"
                    id="specialization"
                    value={formData.specialization}
                    onChange={handleChange}
                >
                    <option value="">Select a specialization</option>
                    {specializations.map((spec) => (
                        <option key={spec.specializationId} value={spec.specializationId}>
                            {spec.specializationName}
                        </option>
                    ))}
                </select>
                <label htmlFor="doctor">Select Doctor:</label>
                <select
                    name="doctor"
                    id="doctor"
                    value={formData.doctor}
                    onChange={handleChange}
                    disabled={!formData.specialization}
                >
                    <option value="">Select a doctor</option>
                    {doctors.map((doc) => (
                        <option key={doc.doctorId} value={doc.doctorId}>
                            {doc.name} {doc.surname}
                        </option>
                    ))}
                </select>
                <label htmlFor="date">Select Date:</label>
                <ReactDatePicker
                    selected={startDate}
                    onChange={handleChangeDate}
                    filterDate={isWeekday}
                    minDate={new Date()}
                    placeholderText="Select a date"
                    style ={{}}
                />
                <label htmlFor="time">Select Time:</label>
                <select name="time" id="time" value={formData.time} onChange={handleChange}>
                    <option value="" disabled>Select a time</option>
                    {Array.from({length: 9}, (_, i) => 10 + i).map((hour) => (
                        <option key={hour} value={`${hour}:00`}>{`${hour}:00`}</option>
                    ))}
                </select>
                <button className={styles.bookingButton} type="submit">BOOK</button>
            </form>
        </div>
      </main>
    </div>
  );
};

export default Booking;