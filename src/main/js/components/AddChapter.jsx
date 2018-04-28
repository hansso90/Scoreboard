import React from 'react';
import TextWithLabel from './TextWithLabel';
import Button from './Button';

const AddChapter = (props) => {
    const nameProps = {
        label: 'Name',
        name: 'addChapter_name',
        actions: props.actions,
        userInputs: props.userInputs
    };

    const buttonProps = {
        label: 'Save',
        onClick: () => props.actions.addChapter(props.userInputs[nameProps.name])
    };

    return (
        <div className="addChapter">
            <TextWithLabel {...nameProps} />
            <Button {...buttonProps} />
        </div>);
};


export default AddChapter;
