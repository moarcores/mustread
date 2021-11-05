import React from 'react';
//import  { Typography } from '@mui/material';
import { createTheme } from '@mui/material';
import './HomePage.scss';
import { Book } from '../Book/book';

export const HomePage = () => {
  const theme = createTheme();
  return (
    <div>
      <h1>
        Best books
      </h1>
      <div style={{ marginTop: theme.spacing(2)}} className="main__container">
        <Book />
        <Book />
        <Book />
        <Book />
        <Book />
      </div>
    </div>
  )
}
