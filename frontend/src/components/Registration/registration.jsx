import React, { useState } from 'react';

import { useFormik } from 'formik';
import axios from 'axios';

import { FormikForm } from '../../common-ui/formik-form/formik-form';
import { ErrorMessage } from '../Error/error';
import { API_URL } from '../../api/api';

const Message = () => (
  <div>
    <p>Success registration! Please <a href="/login">login</a> </p>
  </div>
);

export const Registration = () => {
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
      axios.post(API_URL + '/users', values)
        .then(res => {
          console.log(res);
          setIsSubmitted(true)
        }).catch(res => {
          console.log(res);
          setIsSubmitted(true);
          setError(true)
      });
    },
  });

  return (
    <FormikForm formik={formik} formName="Sign up" isSubmitted={isSubmitted}>
      {error ? <ErrorMessage /> : <Message />}
    </FormikForm>
  );
}
