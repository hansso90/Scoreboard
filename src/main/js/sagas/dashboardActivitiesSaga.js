
import { takeLatest, call, put, select } from 'redux-saga/effects';
import actions from '../actions/index';
import { getDashboardActivities } from '../services/dashboardActivityService';
import { REQUIRE_DASHBOARDACTIVITIES } from '../actions/types';

export const getToken = state => state.authorization.token;
const { onReceiveDashboardActivities, receiveDashboardActivityError, unAuthorized } = actions;

function* getAllDashboardActivities() {
    try {
        const token = yield select(getToken);
        const dashboardDashboardActivitiesResponse = yield call(getDashboardActivities, token);

        if(dashboardDashboardActivitiesResponse.ok) {
            const dashboardDashboardActivities = yield call([dashboardDashboardActivitiesResponse, 'json']);
            yield put(onReceiveDashboardActivities(dashboardDashboardActivities));
            return;
        }

        if(dashboardDashboardActivitiesResponse.status === 403 || dashboardDashboardActivitiesResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([dashboardDashboardActivitiesResponse, 'text']);
            yield put(receiveDashboardActivityError(message));
        }
    }
    catch(e) {
        yield put(receiveDashboardActivityError(e.message));
        yield put(unAuthorized());
    }
}


export default function* dashboardActivitiesSaga() {
    yield takeLatest(REQUIRE_DASHBOARDACTIVITIES, getAllDashboardActivities);
}
