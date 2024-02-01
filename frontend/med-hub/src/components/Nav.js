import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import styles from './Nav.module.css';

const Nav = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('auth_token');
    navigate('/');
  };

  return (
    <nav className={styles.navMain}>
      <ul className={styles.navList}>
        <li className={styles.navItem}>
          <NavLink to="/mainpage" className={({ isActive }) => isActive ? styles.navActiveLink : styles.navLink}>
            <i className={`fa-solid fa-house ${styles.navIcon}`}></i> HOME
          </NavLink>
        </li>
        <li className={styles.navItem}>
          <NavLink to="/schedule" className={({ isActive }) => isActive ? styles.navActiveLink : styles.navLink}>
            <i className={`fa-solid fa-calendar-days ${styles.navIcon}`}></i> SCHEDULE
          </NavLink>
        </li>
        <li className={styles.navItem}>
          <NavLink to="/adding" className={({ isActive }) => isActive ? styles.navActiveLink : styles.navLink}>
            <i className={`fa-solid fa-user ${styles.navIcon}`}></i> ADDING
          </NavLink>
        </li>
        <li className={styles.navItem}>
          <button onClick={handleLogout} className={styles.navButton}>
            <i className={`fa-solid fa-right-from-bracket ${styles.navIcon}`}></i>
          </button>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
