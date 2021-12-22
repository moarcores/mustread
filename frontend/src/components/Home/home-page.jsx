/* eslint-disable no-unused-vars */
import React, { useState, useEffect } from 'react';
//import  { Typography } from '@mui/material';
import { useSelector, useDispatch } from 'react-redux';
import axios from 'axios';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import TestImg from '../../assets/img/img.png';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Rating from '@mui/material/Rating';

// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react/swiper-react';

// Import Swiper styles
import 'swiper/swiper.min.css';
import 'swiper/modules/pagination/pagination.min.css';
import 'swiper/modules/navigation/navigation.min.css';

import SwiperCore, {
  Pagination,Navigation
} from 'swiper';

import { CardActionArea, createTheme } from '@mui/material';

import { Book } from '../Book/book';
import { getConfig, API_URL } from '../../api/api';

import firstBook from '../../assets/img/1984.png';
import secondBook from '../../assets/img/cook.png';
import thirdBook from '../../assets/img/kotl.png';
import fourthBook from '../../assets/img/cpp.png';

import './swiper-custom.css';
import './HomePage.css';

const PopularBook = ({ img, name, authorName, description, rating }) => {
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardActionArea>
        <div style={{ display: 'flex', 'flex-direction' : 'column'}}>
          <CardMedia
            component="img"
            height="250"
            width="250"
            image={img}
            alt="green iguana"
          />
          <CardContent>
            <Typography gutterBottom variant="h4" component="div">
              {name}
            </Typography>
            <Typography gutterBottom variant="h6" component="div">
              {authorName}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {description}
            </Typography>
            <Rating name="read-only" value={rating} readOnly />
          </CardContent>
        </div>
      </CardActionArea>
    </Card>
  );
};

SwiperCore.use([Pagination, Navigation]);

export const HomePage = () => {
  //const dispath = useDispatch();

  const authToken = useSelector(state => state.login.token);

  const [books, setBooks] = useState([]);

  useEffect(() => {
    const config = getConfig(authToken);
    //TODO change to feed
    axios.get(API_URL + '/userbook/feed', config)
      .then((res) => {
        console.log('kek', res.data);
        setBooks(res.data)
      })
      .catch(res => {
        console.log(res)
      })
  }, [authToken])

  const theme = createTheme();
  return (
    <div>
      <h1>Popular now</h1>
      <div>
        <Swiper slidesPerView={3} spaceBetween={30} slidesPerGroup={3} loop={true} loopFillGroupWithBlank={true} pagination={{
          'clickable': true
        }} navigation={true} className="mySwiper">
          <SwiperSlide>
            <PopularBook
              img={firstBook}
              name={'1984'}
              authorName={'George Orwell'}
              description={'Big brother is watching you'}
              rating={4.7}
            />
          </SwiperSlide>
          <SwiperSlide>
            <PopularBook
              img={fourthBook}
              name={'С++ in Depth'}
              authorName={'Bjarne Stroustrup'}
              description={'Best programming language'}
              rating={5}
            />
          </SwiperSlide>
          <SwiperSlide>
            <PopularBook
              img={thirdBook}
              name={'Котлован'}
              authorName={'Андрей Платонов'}
              description={'Антиутопия'}
              rating={3.7}
            />
          </SwiperSlide>
          <SwiperSlide>
            <PopularBook
              img={secondBook}
              name={'The cooking book'}
              authorName={'Nikita Podhavov'}
              description={'Best dishes'}
              rating={4.0}
            />
          </SwiperSlide>
        </Swiper>
      </div>
      <h1>
        Feed
      </h1>
      <div style={{ marginTop: theme.spacing(2)}} className="main__container">
        {books && books.map((book) =>
          (
            <Book
              key={book.readBook.bookModel.id}
              id={book.readBook.bookModel.id}
              name={book.readBook.bookModel.title}
              authorName={book.readBook.bookModel.author}
              img={book.readBook.bookModel.pictureLink}
              description={book.userName}
            />
          ))}
      </div>
    </div>
  )
}
