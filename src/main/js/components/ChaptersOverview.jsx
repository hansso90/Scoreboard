import React from 'react';
import DeletableChapter from './DeletableChapter';

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
            <span>Chapters:</span>
            {chapters.map((a, index) => {
                return (<DeletableChapter
                    chapter={{ chapterName: a.name, chapterColor: 'black' }}
                    actions={props.actions}
                />);
            })}

        </div>);
};
export default ChaptersOverview;
