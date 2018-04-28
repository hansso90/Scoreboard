import propTypes from 'prop-types';
import React from 'react';
import Text from './Text';
import Label from './Label';

const TextWithLabel = (props) => {
    return (
        <div className="textWithLabel">
            <Label {...props} />
            {!props.readOnly &&
            <Text {...props} /> }
            {props.readOnly &&
            <Label label={props.userInputs[props.name]} />
            }
        </div>);
};


export default TextWithLabel;
const {
    string, bool, object//, objectOf, func, , bool, shape
} = propTypes;

TextWithLabel.propTypes = {
    label: string,
    name: string.isRequired,
    readOnly: bool,
    actions: object.isRequired,
    userInputs: object.isRequired
};

TextWithLabel.defaultProps = {
    label: '',
    readOnly: false
};
