/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';

import { Formik, Field, Form, useFormik } from 'formik';
import { useSelector } from 'react-redux';

import { getConfig, API_URL } from '../../api/api';
import axios from 'axios';
import { Button, TextField, Box } from '@mui/material';

import { UserCard } from './UserCard';

export const Users = () => {
  const authToken = useSelector(state => state.login.token);

  const [users, setUsers] = useState([]);

  const subscribeToUser = (id) => {
    const config = getConfig(authToken);
    axios.post(API_URL + '/subscription/', { id: id }, config)
      .then((res) => {
        console.log(res);
      })
      .catch(res => {
        console.log(res);
      })
  };


  const formik = useFormik({
    initialValues: {
      username: '',
    },
    /*    validationSchema: validationSchema,*/
    onSubmit: (values) => {
      const config = getConfig(authToken);
      console.log(values)
      axios.get(API_URL + '/users/search/' + values.username, config)
        .then(res => {
          console.log('res', res.data.data)
          setUsers(res.data)
        })
        .catch(res => {
          console.log(res)
        })
    },
  });

  useEffect(() => {
    const config = getConfig(authToken);
    axios.get(API_URL + '/users/1', config)
      .then(res => {
        console.log(res);
      }).catch(res => {
      console.log(res);
    });
  },[]);
  console.log('uuu', users)
  return (
    <>
      <div>
      <h1>Users</h1>
      <Box  component="form" onSubmit={formik.handleSubmit}>
        <TextField
          fullWidth
          id="username"
          name="username"
          value={formik.values.username}
          onChange={formik.handleChange}
          placeholder="username..."
        />
        <Button style={{ marginTop: '2rem' }} variant='contained' type="submit">search</Button>
      </Box>
    </div>
      <div style={{ display: 'flex', gap: '20px'}}>
        {users.length !== 0 && users.map(user => (
          <UserCard key={user.id} user={user} />
        ))}
      </div>
    </>
  )
}