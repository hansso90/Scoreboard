
import { takeLatest, call, put } from 'redux-saga/effects';
import { push } from 'react-router-redux';
import { createBrowserHistory } from 'history';
import actions from '../actions/index';
import { login } from '../services/authorizationService';
import { UN_AUTHORIZED, DO_LOGIN } from '../actions/types';
import { receiveToken } from '../actions/authorizationActions';

const { clearLoginError, receiveLoginError } = actions;
const history = createBrowserHistory();
function* onUnAuthorized() {
    history.push('/login');
}

function* onDoLogin(message) {
    try {

        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('response niet ok');
            yield put(receiveLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
        } else {
            console.log('response ok');
            const tokenJson = yield result.json();
            const token = tokenJson.access_token;

            yield put(receiveToken(token));
            history.push('/dashboard');
        }
    } catch(e) {
        console.log(e);

        yield put(receiveLoginError(e.message));
    }
}


export default function* authorizationSaga() {
    yield takeLatest(DO_LOGIN, onDoLogin);
    yield takeLatest(UN_AUTHORIZED, onUnAuthorized);
}
