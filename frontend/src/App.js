import React from 'react';
import { Route, Routes } from 'react-router-dom';

import Container from '@mui/material/Container';
import { createTheme } from '@mui/material';
import { Header } from './components/Header';
import { HomePage } from './components/Home';
import { Read } from './components/Read';

import './App.scss';

function App() {
  const theme = createTheme();
  return (
    <Container maxWidth="lg">
        <Header />
      <div className="main" style={{ marginTop: theme.spacing(3) }}>
        <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route path="/read" element={<Read />}/>
        </Routes>
      </div>
    </Container>
  );
}

export default App;
