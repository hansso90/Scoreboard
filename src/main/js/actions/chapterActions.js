import { REQUIRE_CHAPTERS, ADD_CHAPTER, RECEIVE_CHAPTERS, RECEIVE_CHAPTER_ERROR, REMOVE_CHAPTER } from './types';

export function requireChapters() {
    return {
        type: REQUIRE_CHAPTERS
    };
}

export function addChapter(name) {
    return {
        type: ADD_CHAPTER,
        name
    };
}

export function removeChapter(id) {
    return {
        type: REMOVE_CHAPTER,
        id
    };
}


export function onReceiveChapters(chapters) {
    return {
        type: RECEIVE_CHAPTERS,
        chapters
    };
}


export function receiveChapterError(message) {
    return {
        type: RECEIVE_CHAPTER_ERROR,
        message
    };
}
