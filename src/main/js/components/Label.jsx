import React from 'react';
import proptypes from 'prop-types';


const Label = ({ label }) => {
    return (<span>{label}</span>);
};

export default Label;

const { string } = proptypes;
Label.propTypes = {
    label: string.isRequired
};

