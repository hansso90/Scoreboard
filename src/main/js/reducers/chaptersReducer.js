import { assignIn } from 'lodash';
import { RECEIVE_CHAPTERS, RECEIVE_CHAPTER_ERROR } from '../actions/types';

const initalState = {
    chapterError: null,
    chapters: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_CHAPTER_ERROR:
            const obj = {
                chapterError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_CHAPTERS:
            return assignIn({}, state, { chapters: action.chapters, chapterError: null });

        default:
            return state;
    }
};
