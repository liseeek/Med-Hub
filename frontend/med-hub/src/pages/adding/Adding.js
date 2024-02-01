import React, { useState, useEffect } from 'react';
import {Helmet} from "react-helmet";
import axios from 'axios';
import styles from './Adding.module.css';
import NavRespo from '../../components/NavRespo';
import logo from '../../img/logo.svg';

const Adding = () => {
    const [formData, setFormData] = useState({
      name: '',
      surname: '',
      locationName: '',
      address: '',
      city: '',
      country: '',
      specializationId: ''
    });
    
    const [specializations, setSpecializations] = useState([]);
    const [messages, setMessages] = useState('');

    useEffect(() => {
        const fetchSpecializations = async () => {
            try {
                const response = await axios.get('/v1/specializations'); // Adjust the endpoint as needed
                setSpecializations(response.data);
            } catch (error) {
                console.error('Failed to fetch specializations', error);
                // Handle error appropriately
            }
        };

        fetchSpecializations();
    }, []);
  
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            const response = await axios.post('/v1/doctors/adding', formData);
            if (response.status === 201) {
                setMessages('Doctor added successfully');
                // Reset form or additional actions
            } else {
                setMessages('Failed to add doctor');
            }
        } catch (error) {
            console.error('Failed to add doctor', error);
            setMessages('Failed to add doctor');
        }
    };
  
    return (
    <div>
        <Helmet>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
        </Helmet>
        <div className={styles.addingBaseContainer}>
        <header className={styles.addingHeader}>
          <div className={styles.addingLogo}>
            <img src={logo} alt="Logo" />
          </div>
          <NavRespo />
        </header>
      </div>
      <main className={styles.addingMain}>
        <div className={styles.addingContainer}>
          <h1 className={styles.addingHeader}>Add Doctor</h1>
          <form className={styles.addingForm} onSubmit={handleSubmit}>
          {messages && <div className={styles.addingMessages}>{messages}</div>}
            <input
              name="name"
              type="text"
              placeholder="Name"
              value={formData.name}
              onChange={handleChange}
            />
            <input
              name="surname"
              type="text"
              placeholder="Surname"
              value={formData.surname}
              onChange={handleChange}
            />
            <input
              name="locationName"
              type="text"
              placeholder="Location Name"
              value={formData.locationName}
              onChange={handleChange}
            />
            <input
              name="address"
              type="text"
              placeholder="Address"
              value={formData.address}
              onChange={handleChange}
            />
            <input
              name="city"
              type="text"
              placeholder="City"
              value={formData.city}
              onChange={handleChange}
            />
            <input
              name="country"
              type="text"
              placeholder="Country"
              value={formData.country}
              onChange={handleChange}
            />
            <label htmlFor="specializations">Choose specialization</label>
            <select
            name="specializationId" // Ensure this matches your backend DTO
            value={formData.specializationId}
            onChange={handleChange}
            >
            {specializations.map((spec) => (
                <option key={spec.specializationId} value={spec.specializationId}>
                    {spec.specializationName}
                </option>
            ))}
            </select>
            <button className={styles.addingButton} type="submit">ADD DOCTOR</button>
          </form>
        </div>
      </main>
    </div>
    );
  };
  
  export default Adding;