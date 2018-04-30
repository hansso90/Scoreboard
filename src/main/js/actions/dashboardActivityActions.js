import { REQUIRE_DASHBOARDACTIVITIES, RECEIVE_DASHBOARDACTIVITIES, RECEIVE_DASHBOARDACTIVITY_ERROR } from './types';

export function requireDashboardActivities() {
    return {
        type: REQUIRE_DASHBOARDACTIVITIES
    };
}

export function onReceiveDashboardActivities(dashboardActivities) {
    return {
        type: RECEIVE_DASHBOARDACTIVITIES,
        dashboardActivities
    };
}


export function receiveDashboardActivityError(message) {
    return {
        type: RECEIVE_DASHBOARDACTIVITY_ERROR,
        message
    };
}
