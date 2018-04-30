import React from 'react';
import propTypes from 'prop-types';
import Button from './Button';
import Chapter from './Chapter';

const DeletableChapter = (props) => {
    const chapter = props.chapter;

    const buttonProps = {
        label: '🗑',
        className: 'btn btn-delete',
        onClick: () => props.actions.removeChapter(chapter.id)
    };

    return (
        <div className="progress-bar col-sm-24 chapter row">
            <span />
            <Chapter {...props} />
            <Button {...buttonProps} />
        </div>);
};

export default DeletableChapter;
const { string, shape } = propTypes;

DeletableChapter.propTypes = {
    chapter: shape({
        name: string,
        color: string,
    }).isRequired
};
