import React from 'react';
import Chapter from './Chapter';

const ChaptersOverview = (props) => {
    const chapterData = props.chapterData;
    const error = chapterData.chapterError;
    const chapters = chapterData.chapters;
    if(!chapters || error) {
        props.actions.requireChapters();
        return (
            <div>
                { error && <span>{error}</span>}
                { !error && <span>Getting chapters</span>}
            </div>);
    }

    return (
        <div className="chaptersOverview overview">
            <span> Received chapters:</span>
            {chapters.map((a, index) => {
                return (<Chapter chapter={a} actions={props.actions} />);
            })}

        </div>);
};
export default ChaptersOverview;
