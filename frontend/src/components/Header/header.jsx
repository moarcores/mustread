import React from 'react';
import { Link } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import { IconButton } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

import { Navbar } from '../Navbar';
import Logo from '../../assets/img/book.png';
import { links } from './navbar-data';

import './Header.css';

export const Header = () => {
  return (
  <header className="header">
      <Grid container alignItems="center">
          <Grid item lg={2} md={4} xs={6}
                className="header__logo"
                display="flex"
                alignItems="center"
          >
              <Link to="/" className="header__home_link">
                  <img src={Logo}  width="40" />
                  <span>MustRead</span>
              </Link>
          </Grid>
          <Grid item lg={9} md={5} xs={12} className="header__login">
                <Navbar links={links} />
          </Grid>
          {/*TODO Nalabat login s pacanami*/}
          <Grid item lg={1} md={3} xs={6} className="header__login">
            <IconButton href="/login">
              <AccountCircleIcon
                color="secondary"
                fontSize="large"
              />
            </IconButton>
          </Grid>
      </Grid>
  </header>
  )
};
