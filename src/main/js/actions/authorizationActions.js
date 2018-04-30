import { UN_AUTHORIZED, DO_LOGIN, RECEIVE_LOGIN_ERROR, CLEAR_LOGIN_ERROR, RECEIVE_TOKEN, DO_LOGOUT, LOGGED_OUT } from './types';

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


export function doLogout() {
    return {
        type: DO_LOGOUT,
    };
}


export function onLoggedOut() {
    return {
        type: LOGGED_OUT,
    };
}

export function receiveLoginError(message) {
    return {
        type: RECEIVE_LOGIN_ERROR,
        message
    };
}

export function receiveToken(token) {
    return {
        type: RECEIVE_TOKEN,
        token
    };
}


export function clearLoginError() {
    return {
        type: CLEAR_LOGIN_ERROR
    };
}
