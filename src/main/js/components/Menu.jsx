import React from 'react';
import { NavLink } from 'react-router-dom';

const Menu = (props) => {
    return (
        <div>
            <ul>
                <NavLink to="/categories" replace >Categories</NavLink>
                <NavLink to="/chapters" replace >Chapters</NavLink>
                <NavLink to="/users" replace >Users</NavLink>
                <NavLink to="/dashboard" replace >Dashboard</NavLink>
            </ul>
        </div>
    );
};

export default Menu;
