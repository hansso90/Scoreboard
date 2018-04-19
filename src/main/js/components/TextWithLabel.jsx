import propTypes from 'prop-types';
import React from 'react';
import Text from './Text';
import Label from './Label';


const TextWithLabel = ({
    label, userInputs, name, actions
}) => {
    const textProps = {
        userInputs,
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
    name: string.isRequired,
    actions: object.isRequired,
    userInputs:object.isRequired
};

TextWithLabel.defaultProps = {
    label: ''
};
