import React from 'react';
import {Helmet} from "react-helmet";
import Nav from '../../components/Nav.js';
import styles from './MainPage.module.css';
import logo from '../../img/logo.svg';
import services from '../../img/services.svg'
import doctor from '../../img/doctor.svg'

function MainPage() {
    const messages = [];

    return (
        <div>
            <Helmet>
            <meta name="viewport" content=""/>
            </Helmet>
            <div className={styles.mainPageBaseContainer}>
            <header className={styles.mainPageHeader}>
                <div className={styles.mainPageLogo}>
                    <img src={logo} alt="Logo" />
                </div>
                <Nav />
            </header>
            <main className={styles.mainPageMain}>
                <div className={styles.mainPageMessages}>
                    {messages.length > 0 && messages.map((message, index) => (
                        <div key={index}>{message}</div>
                    ))}
                </div>
                <div className={styles.mainPageContentContainer}>
                    <div className={styles.mainPageLeftSection}>
                        <div className={styles.mainPageHealth}>
                            <h1>Your HEALTH,<br />simplified.</h1>
                            <p>Get the care you need, when you need it</p>
                        </div>
                        <div className={styles.mainPageServices}>
                            <div className={styles.mainPageButtonContainer}>
                                <button className={styles.mainPageBookButton} onClick={() => window.location.href = 'booking'}>BOOK NOW</button>
                                <button className={styles.mainPageContactButton} onClick={() => {/* Handle contact click */}}>CONTACT US</button>
                            </div>
                            <h1>Services</h1>
                            <img src={services} alt="Services" />
                        </div>
                    </div>
                    <div className={styles.mainPageRightSection}>
                        <img src={doctor} alt="Doctor" />
                    </div>
                </div>
            </main>
        </div>
    </div>
    );
}

export default MainPage;