import { REQUIRE_ACTIVITIES, RECEIVE_ACTIVITIES, RECEIVE_ACTIVITY_ERROR } from './types';

export function requireActivities() {
    return {
        type: REQUIRE_ACTIVITIES
    };
}

export function onReceiveActivities(activities) {
    return {
        type: RECEIVE_ACTIVITIES,
        activities
    };
}


export function receiveActivityError(message) {
    return {
        type: RECEIVE_ACTIVITY_ERROR,
        message
    };
}
