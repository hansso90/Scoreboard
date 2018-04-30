import React from 'react';
import { NavLink, Redirect } from 'react-router-dom';
import upperFirst from 'lodash/upperFirst';

const Menu = (props) => {

    if(!props.currentUser) {
        return (<Redirect to="/login" />);
    }

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
