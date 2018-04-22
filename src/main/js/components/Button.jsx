import React from 'react';
import proptypes from 'prop-types';


const Button = ({ label, onClick }) => {
    const _onClick = () => {
        onClick();
    };
    return (<button onClick={_onClick} >{label}</button>);
};

export default Button;

const { string, func } = proptypes;
Button.propTypes = {
    label: string.isRequired,
    onClick: func.isRequired
};

