import { fork } from 'redux-saga/effects';
import activitiesSaga from './activitiesSaga';
import categoriesSaga from './categoriesSaga';
import chaptersSaga from './chaptersSaga';
import authorizationSaga from './authorizationSaga';

export default function* () {
    yield fork(activitiesSaga);
    yield fork(categoriesSaga);
    yield fork(chaptersSaga);
    yield fork(authorizationSaga);
}
