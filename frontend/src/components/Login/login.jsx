import React, { useState } from 'react';

import { useFormik } from 'formik';
import axios from 'axios';
import { useDispatch } from 'react-redux';

import { FormikForm } from '../../common-ui/formik-form/formik-form';
import { setToken } from '../../state/loginSlice';
import { ErrorMessage } from '../Error/error';

import { API_URL } from '../../api/api';

const Message = () => (
  <div>
    <p>Success login! Check <a href="/">feed</a>! </p>
  </div>
);

export const Login = () => {
  const dispatch = useDispatch();

  const [isSubmitted, setIsSubmitted] = useState(false);
  const [error, setError] = useState(false);
  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',
    },
    /*    validationSchema: validationSchema,*/
    onSubmit: (values) => {
      console.log(JSON.stringify(values))
      axios.post(API_URL + '/users/auth', values)
        .then(res => {
          console.log(res.data.accessToken);
          dispatch(setToken(res.data.accessToken))
          setIsSubmitted(true);
        })
        .catch((res)=> {
          console.log(res.data);
          setIsSubmitted(true);
          setError(true)
        });
    },
  });

  return (
    <FormikForm formik={formik} formName="Sign in" isSubmitted={isSubmitted}>
      {error ? <ErrorMessage /> : <Message />}
    </FormikForm>
  );
}
