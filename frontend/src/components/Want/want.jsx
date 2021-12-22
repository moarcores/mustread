/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from 'react';
//import  { Typography } from '@mui/material';
import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';
// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react/swiper-react';

// Import Swiper styles
import 'swiper/swiper.min.css';
import 'swiper/modules/pagination/pagination.min.css';
import 'swiper/modules/navigation/navigation.min.css';

import { CardActionArea, createTheme } from '@mui/material';

import { Book } from '../Book/book';
import { getConfig, API_URL } from '../../api/api';

export const Want = () => {
  //const dispath = useDispatch();

  const authToken = useSelector(state => state.login.token);

  const [books, setBooks] = useState([]);

  useEffect(() => {
    const config = getConfig(authToken);
    //TODO change to feed
    axios.get(API_URL + '/userbook/wantlist', config)
      .then((res) => {
        console.log(res.data);
        setBooks(res.data)
      })
      .catch(res => {
        console.log(res)
      })
  }, [authToken])
  const theme = createTheme();
  console.log('books', books)
  return (
    <div>
      <h1>Want</h1>
      <div style={{ marginTop: theme.spacing(2)}} className="main__container">
        {books.length !== 0 && books.bookModels.map((book) =>
          (
            <Book
              key={book.id}
              id={book.id}
              name={book.title}
              authorName={book.author}
              img={book.pictureLink}
            />
          ))}
      </div>
    </div>
  )
}
