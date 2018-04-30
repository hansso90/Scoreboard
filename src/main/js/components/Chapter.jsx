import React from 'react';
import propTypes from 'prop-types';

const Chapter = (props) => {
    const chapter = props.chapter;

    return (
        <div className="label">{chapter.chapterName}</div>
    );
};

export default Chapter;
const { string, shape } = propTypes;

Chapter.propTypes = {
    chapter: shape({
        chapterName: string,
        chapterColor: string
    }).isRequired
};
Chapter.DefaultValue = {
    chapter: shape({
        chapterName: '',
        chapterColor: ''
    })
};
