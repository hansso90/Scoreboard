import React from 'react';
import propTypes from 'prop-types';
import LabelWithLabel from './LabelWithLabel';
import Button from './Button';

const Chapter = (props) => {
    const chapter = props.chapter;
    const nameProps = {
        label: 'Name',
        text: chapter.name
    };

    const buttonProps = {
        label: '🗑',
        className: "btn btn-delete",
        onClick: () => props.actions.removeChapter(chapter.id)
    };

    return (
        <div className="chapter row">
            <LabelWithLabel {...nameProps} />
            <Button {...buttonProps} />
        </div>);
};

export default Chapter;
const { string, shape } = propTypes;

Chapter.propTypes = {
    chapter: shape({
        name: string,
        defaultStardust: string
    }).isRequired
};
