/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';

import { Formik, Field, Form } from 'formik';
import { useSelector } from 'react-redux';

import { getConfig, API_URL } from '../../api/api';
import axios from 'axios';
import { Button, TextField } from '@mui/material';

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

  useEffect(() => {
    const config = getConfig(authToken);
    axios.get(API_URL + '/users/1', config)
      .then(res => {
        console.log(res);
        setUsers(res)
      }).catch(res => {
      console.log(res);
    });
  },[]);

  return (
    <>  <div>
      <h1>Users</h1>
      <Formik
        initialValues={{
          username: ''
        }}
        onSubmit={(values) => {
          const config = getConfig(authToken);
          axios.post(API_URL + '/users/search', {}, config)
            .then(res => {
              console.log(res)
            })
            .catch(res => {
              console.log(res)
            })
        }}
      >
        <Form>
          <TextField fullWidth id="username" name="username" placeholder="username..." />
          <Button style={{ marginTop: '2rem' }} variant='contained' type="submit">search</Button>
        </Form>
      </Formik>
    </div>
    </>
  )
}