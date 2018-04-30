import React from 'react';
import propTypes from 'prop-types';

const Chapter = (props) => {
    const chapter = props.chapter;

    return (
            <span className="rockhand">
                <div className="label">{chapter.chapterName}</div>
                <div className={`pointer ${chapter.chapterColor}`}>🤘</div>
            </span>
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
