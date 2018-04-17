import propTypes from 'prop-types';
import React from 'react';
import Text from './Text';
import Label from './Label';


const TextWithLabel = ({
    label, text, name, actions
}) => {
    const textProps = {
        text,
        name,
        actions
    };
    const labelProps = {
        label
    };

    return (
        <div>
            <Label {...labelProps} />
            <Text {...textProps} />
        </div>);
};


export default TextWithLabel;
const {
    string, object//, objectOf, func, , bool, shape
} = propTypes;

TextWithLabel.propTypes = {
    label: string,
    text: string.isRequired,
    name: string.isRequired,
    actions: object.isRequired
};

TextWithLabel.defaultProps = {
    label: ''
};
