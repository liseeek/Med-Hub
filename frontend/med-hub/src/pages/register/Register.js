import React, { useState } from 'react';
import {Helmet} from "react-helmet";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import logo from '../../img/logo.svg';
import styles from './Register.module.css';

const Register = () => {
    const [user, setUser] = useState({
        name: '',
        surname: '',
        email: '',
        password: '',
        confirmedPassword: '',
        phoneNumber: ''
    });
    const [messages, setMessages] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (user.password !== user.confirmedPassword) {
            setMessages('Passwords do not match');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/v1/users/registration', user);
            // Handle response here
            if (response.status === 200 || response.status === 201) {
                setMessages('User registered successfully');
                navigate('/');
            }
        } catch (error) {
            if (error.response) {
                // Request made and server responded with a status code outside the range of 2xx
                setMessages(error.response.data.message || 'Error during registration');
            } else if (error.request) {
                // The request was made but no response was received
                setMessages('No response from server');
            } else {
                // Something happened in setting up the request that triggered an Error
                setMessages('Error: ' + error.message);
            }
        }
    };

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    return (
    <div>
            <Helmet>
                <meta name="viewport" content="" />
            </Helmet>
            <div className={styles.registerContainer}>
            <div className={styles.registerLogo}>
                <img src={logo} alt="Logo" />
            </div>
            <div className={styles.registerContent}>
            <h1 className={styles.registerTitle}>Create an Account</h1>
                <form className={styles.registerForm} onSubmit={handleSubmit}>
                    {messages && <div className={styles.registerMessages}>{messages}</div>}
                    <input
                        className={styles.registerInput}
                        name="name"
                        type="text"
                        placeholder="Name"
                        value={user.name}
                        onChange={handleChange}
                        required
                    />
                    <input
                        className={styles.registerInput}
                        name="surname"
                        type="text"
                        placeholder="Surname"
                        value={user.surname}
                        onChange={handleChange}
                        required
                    />
                    <input
                        className={styles.registerInput}
                        name="email"
                        type="email"
                        placeholder="Email@email.com"
                        value={user.email}
                        onChange={handleChange}
                        required
                    />
                    <input
                        className={styles.registerInput}
                        name="password"
                        type="password"
                        placeholder="Password"
                        value={user.password}
                        onChange={handleChange}
                        required
                    />
                    <input
                        className={styles.registerInput}
                        name="confirmedPassword"
                        type="password"
                        placeholder="Confirm Password"
                        value={user.confirmedPassword}
                        onChange={handleChange}
                        required
                    />
                    <input
                        className={styles.registerInput}
                        name="phoneNumber"
                        type="text"
                        placeholder="Phone Number"
                        value={user.phoneNumber}
                        onChange={handleChange}
                        required
                    />
                    <button className={styles.registerButton} type="submit">REGISTER</button>
                </form>
            </div>
        </div>
    </div>
    );
};

export default Register;
