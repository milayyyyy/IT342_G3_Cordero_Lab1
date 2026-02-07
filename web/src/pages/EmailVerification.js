import React, { useState, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { authAPI } from '../api/apiClient';
import '../styles/auth.css';

const EmailVerification = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { login } = useAuth();

  const email = searchParams.get('email');
  const token = searchParams.get('token');

  const [status, setStatus] = useState('verifying');
  const [error, setError] = useState('');

  useEffect(() => {
    const verifyEmail = async () => {
      try {
        const response = await authAPI.verifyEmail(email, token);
        login(response.data, response.data.token);
        setStatus('success');
        setTimeout(() => navigate('/dashboard'), 2000);
      } catch (err) {
        setError(err.response?.data?.message || 'Failed to verify email');
        setStatus('error');
      }
    };

    if (email && token) {
      verifyEmail();
    }
  }, [email, token, login, navigate]);

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h1>Email Verification</h1>
        
        {status === 'verifying' && (
          <div className="loading-message">
            <p>Verifying your email...</p>
            <div className="spinner"></div>
          </div>
        )}

        {status === 'success' && (
          <div className="success-message">
            <p>Email verified successfully! Redirecting to dashboard...</p>
          </div>
        )}

        {status === 'error' && (
          <div>
            <div className="error-message">{error}</div>
            <p className="auth-link">
              <a href="/login">Back to Login</a>
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default EmailVerification;
