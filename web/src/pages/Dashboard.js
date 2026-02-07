import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { userAPI, authAPI } from '../api/apiClient';
import '../styles/dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const { logout } = useAuth();
  const [userProfile, setUserProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [darkMode, setDarkMode] = useState(localStorage.getItem('darkMode') === 'true');

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await userAPI.getMe();
        setUserProfile(response.data);
      } catch (err) {
        setError('Failed to load user profile');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, []);

  useEffect(() => {
    if (darkMode) {
      document.body.classList.add('dark-mode');
    } else {
      document.body.classList.remove('dark-mode');
    }
    localStorage.setItem('darkMode', darkMode);
  }, [darkMode]);

  const handleLogout = async () => {
    try {
      await authAPI.logout();
    } catch (err) {
      console.error('Logout API error:', err);
    }
    logout();
    navigate('/login');
  };

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
  };

  const formatDate = (timestamp) => {
    if (!timestamp) return 'N/A';
    const date = new Date(timestamp);
    return date.toLocaleDateString('en-US', { 
      year: 'numeric', 
      month: 'long', 
      day: 'numeric' 
    });
  };

  if (loading) {
    return <div className="loading">Loading...</div>;
  }

  return (
    <div className="dashboard-container">
      <nav className="navbar">
        <h1>Dashboard</h1>
        <div className="navbar-actions">
          <button className="theme-toggle" onClick={toggleDarkMode}>
            {darkMode ? '☀️ Light' : '🌙 Dark'}
          </button>
          <button className="settings-btn" onClick={() => navigate('/settings')}>
            ⚙️ Settings
          </button>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </nav>

      <div className="dashboard-card">
        <h2>Welcome back, {userProfile?.firstName}!</h2>
        {error && <div className="error-message">{error}</div>}
        
        <div className="profile-section">
          <h3>Your Profile</h3>
          <div className="profile-info">
            <div className="info-item">
              <label>Email:</label>
              <p>{userProfile?.email}</p>
            </div>
            <div className="info-item">
              <label>Full Name:</label>
              <p>{userProfile?.firstName} {userProfile?.lastName}</p>
            </div>
            <div className="info-item">
              <label>Account Status:</label>
              <p className={userProfile?.isActive ? 'status-active' : 'status-inactive'}>
                {userProfile?.isActive ? 'Active' : 'Inactive'}
              </p>
            </div>
            <div className="info-item">
              <label>Member Since:</label>
              <p>{formatDate(userProfile?.createdAt)}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
