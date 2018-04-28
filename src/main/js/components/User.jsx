import React from 'react';
import propTypes from 'prop-types';
import LabelWithLabel from './LabelWithLabel';
import Button from './Button';

const User = (props) => {
    const user = props.user;
    const nameProps = {
        label: 'Name',
        text: user.name
    };

    const buttonProps = {
        label: '🗑',
        className: "btn btn-delete",
        onClick: () => props.actions.removeUser(user.id)
    };

    return (
        <div className="user row">
            <LabelWithLabel {...nameProps} />
            <Button {...buttonProps} />
        </div>);
};

export default User;
const { string, shape } = propTypes;

User.propTypes = {
    user: shape({
        name: string,
        defaultStardust: string
    }).isRequired
};
