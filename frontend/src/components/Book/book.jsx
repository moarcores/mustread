import React from 'react';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Rating from '@mui/material/Rating';
import { CardActionArea } from '@mui/material';
import TestImg from '../../assets/img/img.png';
export const Book = () => {
  return (
      <Card sx={{ maxWidth: 345 }}>
        <CardActionArea>
          <div style={{ display: 'flex'}}>
            <CardMedia
                component="img"
                height="180"
                image={TestImg}
                alt="green iguana"
            />
            <CardContent>
              <Typography gutterBottom variant="h5" component="div">
                Lizard
              </Typography>
              <Typography variant="body2" color="text.secondary">
                Lizards are a widespread group of squamate reptiles, with over 6,000
                species, ranging across all continents except Antarctica
              </Typography>
              <Rating name="read-only" value="4.35" readOnly />
            </CardContent>
          </div>
        </CardActionArea>
      </Card>
  )
}