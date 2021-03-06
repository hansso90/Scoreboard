import { assignIn } from 'lodash';
import { RECEIVE_ACTIVITIES, RECEIVE_ACTIVITY_ERROR, LOGGED_OUT } from '../actions/types';

const initalState = {
    activityError: null,
    activities: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_ACTIVITY_ERROR:
            const obj = {
                activityError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_ACTIVITIES:
            return assignIn({}, state, { activities: action.activities, activityError: null });
        case LOGGED_OUT:
            return initalState;
        default:
            return state;
    }
};
