import React, { useState } from 'react';
import axios from 'axios';

import { useSelector } from 'react-redux';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import CardActions from '@mui/material/CardActions';
import { Button } from '@mui/material';

import { API_URL, getConfig } from '../../api/api';

export const UserCard = (user) => {
  const authToken = useSelector(state => state.login.token);


  const subscribeToUser = (id) => {
    const config = getConfig(authToken);
    axios.post(API_URL + '/subscription/' + id, {}, config)
      .then(res => {
        console.log(res);
        setIsSubscribed(true);
      })
      .then(res => {
        console.log(res);
      })
  }
  console.log('sfdfds', user.user.id)
  const [isSubscribed, setIsSubscribed] = useState(false);
  return (
    <Card sx={{ minWidth: 275 }} key={user.id} style={{ width: '100px', marginTop: '2rem' }}>
      <CardContent>
        <Typography gutterBottom variant="h6" component="div">
          Username:
        </Typography>
        <Typography gutterBottom variant="h4" component="div">
          {user.user.username}
        </Typography>
      </CardContent>
      <CardActions>
        <Button disabled={isSubscribed} variant='contained' size="small" onClick={() => {subscribeToUser(user.user.id)}}>
          {isSubscribed ? 'subscribed' : 'subscribe'}
        </Button>
      </CardActions>
    </Card>
  )
}