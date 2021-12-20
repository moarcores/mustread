/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from 'react';
//import  { Typography } from '@mui/material';
import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';
import { createTheme } from '@mui/material';

import { Book } from '../Book/book';
import { getConfig, API_URL } from '../../api/api';

import './HomePage.scss';



export const HomePage = () => {
  //const dispath = useDispatch();

  const authToken = useSelector(state => state.login.token);

  const [books, setBooks] = useState([]);

  useEffect(() => {
    const config = getConfig(authToken);

    axios.get(API_URL + '/userbook/feed', config)
      .then((res) => {
        console.log(res);
      })
      .catch(res => {
        console.log(res)
      })
  }, [authToken])

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
