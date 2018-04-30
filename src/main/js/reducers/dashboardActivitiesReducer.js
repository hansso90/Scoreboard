import { assignIn } from 'lodash';
import { RECEIVE_DASHBOARDACTIVITIES, RECEIVE_DASHBOARDACTIVITY_ERROR } from '../actions/types';

const initalState = {
    dashboardActivityError: null,
    dashboardActivities: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_DASHBOARDACTIVITY_ERROR:
            const obj = {
                dashboardActivityError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_DASHBOARDACTIVITIES:
            return assignIn({}, state, { dashboardActivities: action.dashboardActivities, dashboardActivityError: null });

        default:
            return state;
    }
};
