import { fork } from 'redux-saga/effects';
import dashboardSaga from './dashboardSaga';
import authorizationSaga from './authorizationSaga';

export default function* () {
    yield fork(dashboardSaga);
    yield fork(authorizationSaga);
}
