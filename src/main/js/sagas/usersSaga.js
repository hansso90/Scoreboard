
import { takeLatest, call, put, select } from 'redux-saga/effects';
import actions from '../actions/index';
import { getUsers, createUser, removeUser } from '../services/userService';
import { REQUIRE_USERS, ADD_USER, REMOVE_USER } from '../actions/types';

export const getToken = state => state.authorization.token;
const { onReceiveUsers, receiveUserError, unAuthorized } = actions;

function* getAllUsers() {
    try {
        const token = yield select(getToken);
        const usersResponse = yield call(getUsers, token);

        if(usersResponse.ok) {
            const users = yield call([usersResponse, 'json']);
            yield put(onReceiveUsers(users));
            return;
        }

        if(usersResponse.status === 403 || usersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([usersResponse, 'text']);
            yield put(receiveUserError(message));
        }
    } catch(e) {
        yield put(receiveUserError(e.message));
        yield put(unAuthorized());
    }
}

function* onAddUser(msg) {
    try {
        const {
            name, username, password, chapterId
        } = msg;
        const token = yield select(getToken);
        const usersResponse = yield call(createUser, token, name, username, password, chapterId);

        if(usersResponse.ok) {
            yield call(getAllUsers);
            return;
        }

        if(usersResponse.status === 403 || usersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([usersResponse, 'text']);
            yield put(receiveUserError(message));
        }
    } catch(e) {
        yield put(receiveUserError(e.message));
        yield put(unAuthorized());
    }
}


function* onRemoveUser(msg) {
    try {
        const { id } = msg;
        const token = yield select(getToken);
        const usersResponse = yield call(removeUser, token, id);

        if(usersResponse.ok) {
            yield call(getAllUsers);
            return;
        }

        if(usersResponse.status === 403 || usersResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([usersResponse, 'text']);
            yield put(receiveUserError(message));
        }
    } catch(e) {
        yield put(receiveUserError(e.message));
        yield put(unAuthorized());
    }
}


export default function* usersSaga() {
    yield takeLatest(REQUIRE_USERS, getAllUsers);
    yield takeLatest(ADD_USER, onAddUser);
    yield takeLatest(REMOVE_USER, onRemoveUser);
}
