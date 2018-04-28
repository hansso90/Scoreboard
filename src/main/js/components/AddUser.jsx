import React from 'react';
import TextWithLabel from './TextWithLabel';
import Button from './Button';
import DropdownWithLabel from './DropdownWithLabel';

const AddUser = (props) => {

    const nameProps = {
        label: 'Name',
        name: 'addUser_name',
        actions: props.actions,
        userInputs: props.userInputs
    };
    const userNameProps = {
        label: 'Username',
        name: 'addUser_userName',
        actions: props.actions,
        userInputs: props.userInputs
    };


    const passwordProps = {
        label: 'Password',
        name: 'addUser_password',
        actions: props.actions,
        blur: true,
        userInputs: props.userInputs
    };

    const chapterOptions = props.chapters.map((chap, index) => {
        return {
            name: chap.name,
            value: chap.id
        };
    });
    const chapterProps = {
        name: 'addUser_chapter',
        label: 'Chapter',
        actions: props.actions,
        userInputs: props.userInputs,
        options: chapterOptions,
        defaultText: 'Select a chapter'
    };
    const buttonProps = {
        label: 'Save',
        onClick: () => props.actions.addUser(
            props.userInputs[nameProps.name],
            props.userInputs[userNameProps.name],
            props.userInputs[passwordProps.name],
            props.userInputs[chapterProps.name]
)
    };

    return (
        <div className="addUser">
            <TextWithLabel {...nameProps} />
            <TextWithLabel {...userNameProps} />
            <TextWithLabel {...passwordProps} />
            <DropdownWithLabel {...chapterProps} />
            <Button {...buttonProps} />
        </div>);
};


export default AddUser;
