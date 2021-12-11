import React from 'react';

import { useFormik } from 'formik';
import axios from 'axios';

import { FormikForm } from '../../common-ui/formik-form/formik-form';

import { API_URL } from '../../api/api';

export const Login = () => {
  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',
    },
    /*    validationSchema: validationSchema,*/
    onSubmit: (values) => {
      console.log(JSON.stringify(values))
      axios.post(API_URL + '/users/auth', values)
        .then(res => console.log(res));
    },
  });

  return (
    <FormikForm formik={formik} formName="Sign in" />
  );
}
