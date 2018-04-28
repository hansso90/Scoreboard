import React from 'react';
import User from './User';

const UsersOverview = (props) => {
    return (
        <div className="usersOverview overview">
            <span>Users:</span>
            {props.users.map((a, index) => {
                return (<User user={a} actions={props.actions} />);
            })}

        </div>);
};
export default UsersOverview;
