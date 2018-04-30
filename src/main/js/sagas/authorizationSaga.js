
import { takeLatest, call, put, select } from 'redux-saga/effects';
import { createBrowserHistory } from 'history';
import actions from '../actions/index';
import { login, logout } from '../services/authorizationService';
import { UN_AUTHORIZED, DO_LOGIN, DO_LOGOUT, LOGGED_OUT } from '../actions/types';
import { receiveToken, onLoggedOut } from '../actions/authorizationActions';

const { clearLoginError, receiveLoginError } = actions;
const history = createBrowserHistory();
function* onUnAuthorized() {
    history.push('/login');
    history.go('/login');
}
export const getToken = state => state.authorization.token;

function* onDoLogin(message) {
    try {
        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('response niet ok');
            yield put(receiveLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
        } else {
            const tokenJson = yield result.json();
            const token = tokenJson.access_token;
            yield put(clearLoginError());
            yield put(receiveToken(token));
            history.push('/dashboard');
            history.go();
        }
    } catch(e) {
        console.log(e);

        yield put(receiveLoginError(e.message));
    }
}

function* onDoLogout() {
    try {
        const token = yield select(getToken);
        const result = yield call(logout, token);
        if(!result.ok && result.status !== 401 && result.status !== 403) {
            console.log('response niet ok');
            yield put(receiveLoginError('Er ging iets mis met uitloggen'));
        } else {
            yield put(onLoggedOut());
            history.push('/login');
            history.go();
        }
    } catch(e) {
        console.log(e);

        yield put(receiveLoginError(e.message));
    }
}


export default function* authorizationSaga() {
    yield takeLatest(DO_LOGIN, onDoLogin);
    yield takeLatest(DO_LOGOUT, onDoLogout);
    yield takeLatest(UN_AUTHORIZED, onUnAuthorized);
}
