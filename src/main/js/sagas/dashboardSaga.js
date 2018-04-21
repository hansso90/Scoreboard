
import { takeLatest, call, put } from 'redux-saga/effects';
import { receiveActivities, receiveActivityError, unAuthorized } from '../actions/index';
import { getActivities } from '../services/activityService';
import { REQUIRE_ACTIVITIES } from '../actions/types';


function* getAllActivities() {
    const activitiesResponse = yield call(getActivities);
    if(activitiesResponse.ok) {
        const activities = yield call(activitiesResponse, 'json');
        yield put(receiveActivities(activities));
        return;
    }

    if(activitiesResponse.status === 403 || activitiesResponse.status === 401) { //unauthorized
        yield put(unAuthorized());
    } else {
        const message = yield call(activitiesResponse, 'text');
        yield put(receiveActivityError(message));
    }
}


export default function* dashboardSaga() {
    yield takeLatest(REQUIRE_ACTIVITIES, getAllActivities);
}
