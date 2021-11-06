import React from 'react';
import { Link } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import './Header.scss';

import { Navbar } from '../Navbar';
import Logo from '../../assets/img/book.png';
import { links } from './navbar-data';

export const Header = () => {
  return (
  <header className="header">
      <Grid container alignItems="center">
          <Grid lg={2} md={4} xs={6}
                className="header__logo"
                display="flex"
                alignItems="center"
          >
              <Link to="/" className="header__home_link">
                  <img src={Logo}  width="40" />
                  <span>Must Read</span>
              </Link>
          </Grid>
          <Grid lg={8} md={5} xs={12} className="header__login">
                <Navbar links={links} />
          </Grid>
          {/*TODO Nalabat login s pacanami*/}
          <Grid lg={2} md={3} xs={6} className="header__login">login</Grid>
      </Grid>
  </header>
  )
};
