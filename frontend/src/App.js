import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';

import {  useSelector } from 'react-redux';

import Container from '@mui/material/Container';
import { createTheme } from '@mui/material';
import { Registration } from './components/Registration';
import { Login } from './components/Login';
import { Header } from './components/Header';
import { HomePage } from './components/Home';
import { Read } from './components/Read';

import './App.scss';

function App() {
  const theme = createTheme();

  const authToken = useSelector(state => state.login.token);
  console.log(authToken)
  return (
    <Container maxWidth="lg">
        <Header />
      <div className="main" style={{ marginTop: theme.spacing(3) }}>
        <Routes>
          <Route path="/registration" element={<Registration />} />
          <Route path="/login" element={<Login />} />
          TODO change to login
          <Route exact path="/" element={authToken === null ? <Navigate to="/login"/> : <HomePage />} />
          <Route path="/read" element={<Read />}/>
        </Routes>
      </div>
    </Container>
  );
}

export default App;
