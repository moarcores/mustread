import React, { useState } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Rating from '@mui/material/Rating';
import { CardActionArea } from '@mui/material';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
//import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import { getConfig, API_URL } from '../../api/api';

/*
function randomIntFromInterval(min, max) { // min and max included
  return Math.floor(Math.random() * (max - min + 1) + min)
}
*/

const ReadBook = (id, authToken) => {
  const config = getConfig(authToken);
  axios.post(API_URL + '/userbook/readBook', { id: id }, config)
    .then(res => {
      console.log(res)
    })
    .catch(res => {
      console.log(res)
    })
}

const WantBook = (id, authToken) => {
  const config = getConfig(authToken);
  axios.post(API_URL + '/userbook/add/' + id , { id: id }, config)
    .then(res => {
      console.log(res)
    })
    .catch(res => {
      console.log(res)
    })
}
export const Book = ({ id, img, name, authorName, description, rating }) => {
  const authToken = useSelector(state => state.login.token);

  const [modalOpen, setModalOpen] = useState(false);
  return (
    <>
      <Card sx={{ maxWidth: 345 }} onClick={() => {setModalOpen(true)}}>
        <CardActionArea>
          <div style={{ display: 'flex', padding: '20px'}}>
            <CardMedia
              component="img"
              height="250"
              width="200"
              image={img}
              alt="green iguana"
            />
            <CardContent>
              <Typography gutterBottom variant="h6" component="div">
                {name}
              </Typography>
              <Typography gutterBottom variant="subtitle2" component="div">
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
      <Dialog className='main__container__dialog' open={modalOpen} onClose={() => {
        setModalOpen(false);
      }}>
        <DialogTitle>{name}</DialogTitle>
        <DialogContent>
          <div style={{ display: 'flex', padding: '30px'}}>
            <img
              style={{ width: '50% !important'}}
              width="50%"
              src={img}
              alt="green iguana"
            />
            <CardContent style={{ marginLeft: '3rem' }}>
              <Typography gutterBottom variant="h4" component="div">
                {name}
              </Typography>
              <Typography gutterBottom variant="h6" component="div">
                {authorName}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Amazing book!
              </Typography>
              <Rating name="read-only" value={rating} readOnly />
            </CardContent>
          </div>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => {ReadBook(id, authToken)}}>Read</Button>
          <Button onClick={() => {WantBook(id, authToken)}}>Want</Button>
        </DialogActions>
      </Dialog>
    </>
  )
}
