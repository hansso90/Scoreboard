import assignIn from 'lodash/assignIn';
import { RECEIVE_USERS, RECEIVE_USER_ERROR, LOGGED_OUT, RECEIVE_CURRENT_USER } from '../actions/types';

const initalState = {
    userError: null,
    users: null,
    currentUser: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_USER_ERROR:
            const obj = {
                userError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_USERS:
            return assignIn({}, state, { users: action.users, userError: null });
        case RECEIVE_CURRENT_USER:
            return assignIn({}, state, { currentUser: action.currentUser, userError: null });
        case LOGGED_OUT:
            return initalState;
        default:
            return state;
    }
};
