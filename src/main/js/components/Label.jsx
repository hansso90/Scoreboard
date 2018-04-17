import React from 'react';
import proptypes from 'prop-types';


const Label = ({ text }) => {
    return <span >{text}</span>;
};

export default Label;

const { string } = proptypes;
Label.propTypes = {
    text: string.isRequired
};
