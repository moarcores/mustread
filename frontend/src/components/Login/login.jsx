import React, { useState } from 'react';

import { useFormik } from 'formik';
import axios from 'axios';

import { FormikForm } from '../../common-ui/formik-form/formik-form';

import { API_URL } from '../../api/api';

export const Login = () => {
  const [isSubmitted, setIsSubmitted] = useState(false);
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
          console.log(res);
          setIsSubmitted(true);
        });
    },
  });

  return (
    <FormikForm formik={formik} formName="Sign in" isSubmitted={isSubmitted} />
  );
}
