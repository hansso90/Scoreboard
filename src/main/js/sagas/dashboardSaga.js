
import { takeLatest, call, put, select } from 'redux-saga/effects';
import actions from '../actions/index';
import { getActivities } from '../services/activityService';
import { REQUIRE_ACTIVITIES } from '../actions/types';

export const getToken = state => state.authorization.token;
const { onReceiveActivities, receiveActivityError, unAuthorized } = actions;

function* getAllActivities() {
    try {
        const token = yield select(getToken);
        const activitiesResponse = yield call(getActivities, token);

        if(activitiesResponse.ok) {
            const activities = yield call([activitiesResponse, 'json']);
            yield put(onReceiveActivities(activities));
            return;
        }

        if(activitiesResponse.status === 403 || activitiesResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([activitiesResponse, 'text']);
            yield put(receiveActivityError(message));
        }
    }
    catch(e) {
        yield put(receiveActivityError(e.message));
        yield put(unAuthorized());
    }
}


export default function* dashboardSaga() {
    yield takeLatest(REQUIRE_ACTIVITIES, getAllActivities);
}
