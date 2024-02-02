import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { getAuthToken } from './axiosHelper';

const ProtectedRoute = () => {
    const isAuthenticated = getAuthToken();
    return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
};

export default ProtectedRoute;