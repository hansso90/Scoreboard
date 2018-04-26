import { REQUIRE_USERS, ADD_USER, RECEIVE_USERS, RECEIVE_USER_ERROR, REMOVE_USER } from './types';

export function requireUsers() {
    return {
        type: REQUIRE_USERS
    };
}

export function addUser(name, username, password, chapterId) {
    return {
        type: ADD_USER,
        name,
        chapterId
    };
}

export function removeUser(id) {
    return {
        type: REMOVE_USER,
        id
    };
}


export function onReceiveUsers(users) {
    return {
        type: RECEIVE_USERS,
        users
    };
}


export function receiveUserError(message) {
    return {
        type: RECEIVE_USER_ERROR,
        message
    };
}
