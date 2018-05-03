import { REQUIRE_ACTIVITIES, RECEIVE_ACTIVITIES, RECEIVE_ACTIVITY_ERROR, ADD_ACTIVITY } from './types';

export function requireActivities() {
    return {
        type: REQUIRE_ACTIVITIES
    };
}

export function addActivity(user, chapterId, categoryId, stardust, description, date) {
    return {
        type: ADD_ACTIVITY,
        user,
        chapterId,
        categoryId,
        stardust,
        description,
        date
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
