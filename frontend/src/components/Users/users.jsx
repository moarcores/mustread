/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';

import { useSelector } from 'react-redux';

import { getConfig, API_URL } from '../../api/api';
import axios from 'axios';

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
    <div>
      users
    </div>
  )
}