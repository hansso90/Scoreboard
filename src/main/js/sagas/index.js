import { fork } from 'redux-saga/effects';
import activitiesSaga from './activitiesSaga';
import dashboardActivitiesSaga from './dashboardActivitiesSaga';
import categoriesSaga from './categoriesSaga';
import chaptersSaga from './chaptersSaga';
import usersSaga from './usersSaga';
import authorizationSaga from './authorizationSaga';

export default function* () {
    yield fork(activitiesSaga);
    yield fork(dashboardActivitiesSaga);
    yield fork(categoriesSaga);
    yield fork(chaptersSaga);
    yield fork(usersSaga);
    yield fork(authorizationSaga);
}
