
import { takeLatest, call, put, select } from 'redux-saga/effects';
import actions from '../actions/index';
import { getChapters, createChapter, removeChapter } from '../services/chapterService';
import { REQUIRE_CHAPTERS, ADD_CHAPTER, REMOVE_CHAPTER } from '../actions/types';

export const getToken = state => state.authorization.token;
const { onReceiveChapters, receiveChapterError, unAuthorized } = actions;

function* getAllChapters() {
    try {
        const token = yield select(getToken);
        const chaptersResponse = yield call(getChapters, token);

        if(chaptersResponse.ok) {
            const chapters = yield call([chaptersResponse, 'json']);
            yield put(onReceiveChapters(chapters));
            return;
        }

        if(chaptersResponse.status === 403 || chaptersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([chaptersResponse, 'text']);
            yield put(receiveChapterError(message));
        }
    } catch(e) {
        yield put(receiveChapterError(e.message));
        yield put(unAuthorized());
    }
}

function* onAddChapter(msg) {
    try {
        const { name, defaultStardust } = msg;
        const token = yield select(getToken);
        const chaptersResponse = yield call(createChapter, token, name, defaultStardust);

        if(chaptersResponse.ok) {
            yield call(getAllChapters);
            return;
        }

        if(chaptersResponse.status === 403 || chaptersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([chaptersResponse, 'text']);
            yield put(receiveChapterError(message));
        }
    } catch(e) {
        yield put(receiveChapterError(e.message));
        yield put(unAuthorized());
    }
}


function* onRemoveChapter(msg) {
    try {
        const { id } = msg;
        const token = yield select(getToken);
        const chaptersResponse = yield call(removeChapter, token, id);

        if(chaptersResponse.ok) {
            yield call(getAllChapters);
            return;
        }

        if(chaptersResponse.status === 403 || chaptersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([chaptersResponse, 'text']);
            yield put(receiveChapterError(message));
        }
    } catch(e) {
        yield put(receiveChapterError(e.message));
        yield put(unAuthorized());
    }
}


export default function* chaptersSaga() {
    yield takeLatest(REQUIRE_CHAPTERS, getAllChapters);
    yield takeLatest(ADD_CHAPTER, onAddChapter);
    yield takeLatest(REMOVE_CHAPTER, onRemoveChapter);
}
