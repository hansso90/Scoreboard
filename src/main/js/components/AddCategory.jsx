import React from 'react';
import TextWithLabel from './TextWithLabel';
import Button from './Button';

const AddCategory = (props) => {
    const twlProps = {
        label: 'Name',
        name: 'addCategory',
        actions: props.actions,
        userInputs: props.userInputs
    };

    const buttonProps = {
        label: 'Add',
        onClick: () => props.actions.addCategory(props.userInputs[twlProps.name])
    };

    return (
        <div>
            <TextWithLabel {...twlProps} />
            <Button {...buttonProps} />
        </div>);
};


export default AddCategory;
