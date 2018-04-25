import propTypes from 'prop-types';
import React from 'react';
import Label from './Label';


const LabelWithLabel = (props) => {
    return (
        <div>
            <Label label={props.label} />
            <Label label={props.text} />
        </div>);
};


export default LabelWithLabel;
const {
    string//, objectOf, func, , bool, shape
} = propTypes;

LabelWithLabel.propTypes = {
    label: string,
    text: string
};

LabelWithLabel.defaultProps = {
    label: '',
    text: ''
};
