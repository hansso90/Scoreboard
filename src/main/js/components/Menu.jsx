import React from 'react';
import {NavLink} from 'react-router-dom';

const Menu = (props) => {
    const endpoints = ["categories", "chapters", "users", "dashboard"];


    return (
        <div className="menu">
            <ul className="row">
                {endpoints.map(name =>
                    <li>
                        <NavLink to={'/' + name} replace>{name}</NavLink>
                    </li>
                )}
            </ul>
        </div>
    );
};

export default Menu;
