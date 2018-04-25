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
        label: '+',
        onClick: () => props.actions.addChapter(props.userInputs[nameProps.name])
    };

    return (
        <div>
            <TextWithLabel {...nameProps} />
            <Button {...buttonProps} />
        </div>);
};


export default AddChapter;
