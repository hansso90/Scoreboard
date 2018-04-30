import React from 'react';
import propTypes from 'prop-types';

const Chapter = (props) => {
    const chapter = props.chapter;

    return (
        <div className="col-sm-24">
            <div className={`progress-bar stripes ${chapter.color}`}>
                <span style={{ width: '30%' }} className="stardustText" />
                <span className="rockhand">
                    <div className="label">{chapter.name}</div>
                    <div className="pointer">🤘</div>
                </span>
            </div>
        </div>
    );
};

export default Chapter;
const { string, shape } = propTypes;

Chapter.propTypes = {
    chapter: shape({
        name: string,
        color: string,
    }).isRequired
};
