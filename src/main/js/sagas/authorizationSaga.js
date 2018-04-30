
import { takeLatest, call, put, select } from 'redux-saga/effects';
import { createBrowserHistory } from 'history';
import actions from '../actions/index';
import { login, logout } from '../services/authorizationService';
import { getCurrentUser } from '../services/userService';
import { UN_AUTHORIZED, DO_LOGIN, DO_LOGOUT, LOGGED_OUT } from '../actions/types';
import { receiveToken, onLoggedOut } from '../actions/authorizationActions';
import { onReceiveCurrentUser } from '../actions/userActions';

const { clearLoginError, receiveLoginError } = actions;
const history = createBrowserHistory();
function* onUnAuthorized() {
    history.push('/login');
    history.go();
}
export const getToken = state => state.authorization.token;

function* onDoLogin(message) {
    try {
        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('token response niet ok');
            yield put(receiveLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
            return;
        }
        const tokenJson = yield result.json();
        const token = tokenJson.access_token;
        const userResult = yield call(getCurrentUser, token);
        if(!userResult.ok) {
            console.log('user response niet ok');
            yield put(receiveLoginError('De gebruiker kon niet worden opgehaald'));
            return;
        }
        const currentUser = yield call([userResult, 'json']);

        yield put(onReceiveCurrentUser(currentUser));
        yield put(clearLoginError());
        yield put(receiveToken(token));
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
