import { assignIn } from 'lodash';
import { RECEIVE_LOGIN_ERROR, CLEAR_LOGIN_ERROR, RECEIVE_TOKEN, LOGGED_OUT } from '../actions/types';

const initalState = {
    loginError: null,
    token: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_LOGIN_ERROR:
            const obj = {
                loginError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_TOKEN:
            const tokenObj = {
                token: action.token,
                loginError: null
            };
            return assignIn({}, state, tokenObj);
        case CLEAR_LOGIN_ERROR:
            return initalState;
        case LOGGED_OUT:
            return initalState;
        default:
            return state;
    }
};
