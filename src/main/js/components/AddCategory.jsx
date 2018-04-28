import React from 'react';
import TextWithLabel from './TextWithLabel';
import Button from './Button';

const AddCategory = (props) => {
    const nameProps = {
        label: 'Name',
        name: 'addCategory_name',
        actions: props.actions,
        userInputs: props.userInputs
    };

    const defaultStardustProps = {
        label: 'Default Stardust',
        name: 'addCategory_defaultStardust',
        actions: props.actions,
        userInputs: props.userInputs
    };

    const buttonProps = {
        label: 'Save',
        onClick: () => props.actions.addCategory(props.userInputs[nameProps.name], props.userInputs[defaultStardustProps.name])
    };

    return (
        <div className="addCategory">
            <TextWithLabel {...nameProps} />
            <TextWithLabel {...defaultStardustProps} />
            <Button {...buttonProps} />
        </div>);
};


export default AddCategory;
