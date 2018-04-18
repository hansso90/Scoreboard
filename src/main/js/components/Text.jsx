import React from 'react';
import propTypes from 'prop-types';

const Text = ({ name, actions, userInputs }) => {
    const _onchange = (e) => {
        actions.updateElement(name, e.value);
    };

    return <input name={name} value={userInputs[name]} onChange={_onchange} />;
};

export default Text;

const { string, object } = propTypes;

Text.propTypes = {
    name: string.isRequired,
    actions: object.isRequired,
    userInputs: object.isRequired
};

