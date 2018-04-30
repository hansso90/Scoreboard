import React from 'react';
import { NavLink } from 'react-router-dom';
import upperFirst from 'lodash/upperFirst';
import Button from './Button';

const Menu = (props) => {
    const endpoints = ['categories', 'chapters', 'users', 'activities', 'dashboard', 'logout'];

    const lis = endpoints.map((name, index) =>
        (
            <li>
                <NavLink to={`/${name}`} replace>{upperFirst(name)}</NavLink>
            </li>));
    return (
        <div className="menu">
            <ul className="row">
                {lis}
            </ul>
        </div>
    );
};

export default Menu;
