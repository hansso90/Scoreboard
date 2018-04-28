import React from 'react';
import proptypes from 'prop-types';


const Label = ({ label }) => {
    return (<span className="label">{label}</span>);
};

export default Label;

const { string } = proptypes;
Label.propTypes = {
    label: string
};

Label.defaultValues = {
    label: ''
};

