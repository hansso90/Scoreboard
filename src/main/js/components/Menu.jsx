import React from 'react';
import {NavLink} from 'react-router-dom';

const Menu = (props) => {
    const endpoints = ["categories", "chapters", "users", "dashboard"];


    return (
        <div className="menu col-sm-12">
            <nav className="navbar navbar-light navbar-expand-sm">
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav">
                        {endpoints.map(name =>
                            <li>
                                <NavLink to={'/' + name} replace>{name}</NavLink>
                            </li>
                        )}
                    </ul>
                </div>
            </nav>
        </div>
    );
};

export default Menu;
