import React from 'react';

import errorImage from '../../assets/img/error.png';

import './error.scss';

export const ErrorMessage = () => (
  <div className="error_block">
    <img className="error_image" alt="error" src={errorImage}/>
    <p>OOPS Something went wrong!</p>
  </div>
)