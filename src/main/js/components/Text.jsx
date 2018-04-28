import React from 'react';
import propTypes from 'prop-types';

const Text = ({
    name, actions, userInputs, blur
}) => {
    const _onchange = (e) => {
        actions.updateElement(name, e.target.value);
    };
    const type = blur ? 'password' : 'input';
    const textProps = {
        value: userInputs[name] !== undefined ? userInputs[name] : '',
        className: "text form-control",
        name,
        type
    };

    return <input {...textProps} onChange={_onchange} />;
};

export default Text;

const { string, object, bool } = propTypes;

Text.propTypes = {
    name: string.isRequired,
    actions: object.isRequired,
    userInputs: object.isRequired,
    blur: bool
};

Text.defaultValues = {
    blur: false
};

