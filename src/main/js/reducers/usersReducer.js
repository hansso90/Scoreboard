import assignIn from 'lodash/assignIn';
import { RECEIVE_USERS, RECEIVE_USER_ERROR, LOGGED_OUT } from '../actions/types';

const initalState = {
    userError: null,
    users: null
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
        case LOGGED_OUT:
            return initalState;
        default:
            return state;
    }
};
