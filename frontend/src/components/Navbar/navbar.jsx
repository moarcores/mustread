import React from 'react';
/*import PropTypes, {shape} from 'prop-types';*/
import { Link } from 'react-router-dom';

import './Navbar.scss';

export const Navbar = ({ links }) => {
    return (
        <div className="navbar">
            {links.map(link => (
                <Link key={link.title} to={link.url} className="navbar__item">
                    {link.title}
                </Link>
            ))}
        </div>
    )
};

/*
Navbar.propTypes = {
    links: PropTypes.arrayOf({
        link: shape({
            url: PropTypes.string,
            title: PropTypes.string
        })
    })
}*/
