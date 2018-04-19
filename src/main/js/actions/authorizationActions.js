import { UN_AUTHORIZED, DO_LOGIN } from './types';

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
