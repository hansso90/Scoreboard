import { assignIn } from 'lodash';
import { RECEIVE_LOGIN_ERROR, CLEAR_LOGIN_ERROR } from '../actions/types';

const initalState = {
    loginError: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_LOGIN_ERROR:
            const obj = {
                loginError: action.message
            };
            return assignIn({}, state, obj);
        case CLEAR_LOGIN_ERROR:
            return initalState;

        default:
            return state;
    }
};
