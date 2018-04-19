import { fork } from 'redux-saga/effects';
import dashboardSaga from './dashboardSaga';

export default function* () {
    yield fork(dashboardSaga);
}
