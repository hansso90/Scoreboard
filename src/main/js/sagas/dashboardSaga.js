
import { takeLatest, call, put } from 'redux-saga/effects';
import { receiveActivities, receiveActivityError, unAuthorized } from '../actions/index';
import { getActivities } from '../services/activityService';
import { REQUIRE_ACTIVITIES, UN_AUTHORIZED, UPDATE_ELEMENT } from '../actions/types';


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

function* onUnAuthorized() {
//ACCESS router here to redirect to login
}

function* updateElementCheck(message) {
    console.log('saga');
    console.log(message);
}


export default function* dashboardSaga() {
    yield takeLatest(UPDATE_ELEMENT, updateElementCheck);
    yield takeLatest(REQUIRE_ACTIVITIES, getAllActivities);
    yield takeLatest(UN_AUTHORIZED, onUnAuthorized);
}
