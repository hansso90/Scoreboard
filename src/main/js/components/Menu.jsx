import React from 'react';
import {NavLink} from 'react-router-dom';
import upperFirst from 'lodash/upperFirst';

const Menu = (props) => {
    const endpoints = ["categories", "chapters", "users", "activities", "dashboard"];


    return (
        <div className="menu">
            <ul className="row">
                {endpoints.map(name =>
                    <li>
                        <NavLink to={'/' + name} replace>{upperFirst(name)}</NavLink>
                    </li>
                )}
            </ul>
        </div>
    );
};

export default Menu;
