import React from 'react';
import proptypes from 'prop-types';


const Button = ({ label, onClick, className }) => {
    const _onClick = () => {
        onClick();
    };
    const buttonClass = className || "button btn btn-lg btn-secondary btn-secondary--outline btn-block submit";
    return (<button className={buttonClass} onClick={_onClick} >{label}</button>);
};

export default Button;

const { string, func } = proptypes;
Button.propTypes = {
    label: string.isRequired,
    onClick: func.isRequired
};

