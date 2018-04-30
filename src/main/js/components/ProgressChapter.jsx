import React from 'react';
import propTypes from 'prop-types';
import Chapter from './Chapter';

const ProgressChapter = (props) => {
    const chapter = props.chapter;

    return (
        <div className="col-sm-24">
            <div className={`progress-bar stripes ${chapter.chapterColor}`}>
                <span style={{ width: `${chapter.stardust / 4}%` }} className="stardustText" />
                <Chapter {...props} />
            </div>
        </div>
    );
};

export default ProgressChapter;
const { string, shape, number } = propTypes;

ProgressChapter.propTypes = {
    chapter: shape({
        chapterName: string,
        chapterColor: string,
        stardust: number
    }).isRequired
};

ProgressChapter.DefaultValue = {
    chapter: shape({
        chapterName: '',
        chapterColor: '',
        stardust: 0
    })
};
