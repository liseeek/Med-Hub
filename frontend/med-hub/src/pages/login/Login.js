import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { setAuthHeader, request, getAuthToken } from '../../helpers/axios_helper';
import { jwtDecode } from 'jwt-decode';
import {Helmet} from "react-helmet";
import logo from '../../img/logo.svg'; 
import styles from './Login.module.css';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [messages, setMessages] = useState('');
    const navigate = useNavigate();

    // useEffect(() => {
    //     const token = getAuthToken();
    //     if (token) {
    //         const decodedToken = jwtDecode(token);
    //         const currentDate = new Date();

    //         // Checking if token is expired
    //         if (decodedToken.exp * 1000 < currentDate.getTime()) {
    //             localStorage.removeItem('auth_token'); // If expired, remove token from localStorage
    //         } else {
    //             navigate('/mainpage'); // If valid, redirect to main page
    //         }
    //     }
    // }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await request('post', '/v1/auth/login', { email, password });
            console.log("Login response:", response); // Log the response
            setAuthHeader(response.data.token);
            navigate('/mainpage');
        } catch (error) {
            console.error("Login error:", error); // Log any error
            setMessages(error.response?.data.message || 'Login failed');
        }
    };

    return (
        <div>
            <Helmet>
                <meta name="viewport" content="" />
            </Helmet>
        <div className={styles.loginContainer}>
            <div className={styles.loginLogo}>
                <img src={logo} alt="Logo" />
            </div>
            <div className={styles.loginInnerContainer}>
                <form className={styles.loginForm} onSubmit={handleSubmit}>
                    {messages && <div className={styles.loginMessages}>{messages}</div>}
                    <input 
                        className={styles.loginInput}
                        name="email" 
                        type="email" 
                        placeholder="email@email.com"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <input 
                        className={styles.loginInput}
                        name="password" 
                        type="password" 
                        placeholder="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button className={styles.loginButton} type="submit">LOGIN</button>
                    <a className={styles.loginRegisterButton} href="/register">Don't have an account? Sign up</a>
                </form>
            </div>
        </div>
        </div>
    );
};

export default Login;