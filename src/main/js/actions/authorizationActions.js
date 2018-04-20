import { UN_AUTHORIZED, DO_LOGIN, RECEIVE_LOGIN_ERROR, CLEAR_LOGIN_ERROR } from './types';

export function unAuthorized() {
    return {
        type: UN_AUTHORIZED
    };
}

export function doLogin(username, password) {
    return {
        type: DO_LOGIN,
        username,
        password
    };
}

export function receiveLoginError(message) {
    return {
        type: RECEIVE_LOGIN_ERROR,
        message
    };
}


export function clearLoginError() {
    return {
        type: CLEAR_LOGIN_ERROR
    };
}
