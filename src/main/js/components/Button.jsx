import React from 'react';
import proptypes from 'prop-types';


const Button = ({ label, onClick }) => {
    const _onClick = () => {
        onClick();
    };
    return (<button className="button btn btn-lg btn-secondary btn-secondary--outline btn-block submit" onClick={_onClick} >{label}</button>);
};

export default Button;

const { string, func } = proptypes;
Button.propTypes = {
    label: string.isRequired,
    onClick: func.isRequired
};

