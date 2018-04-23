
import { takeLatest, call, put } from 'redux-saga/effects';
import { createBrowserHistory } from 'history';
import actions from '../actions/index';
import { login } from '../services/authorizationService';
import { getActivities, getActivityById } from '../services/activityService';
import { UN_AUTHORIZED, DO_LOGIN } from '../actions/types';
import { receiveToken } from '../actions/authorizationActions';

const { clearLoginError, receiveLoginError } = actions;
const history = createBrowserHistory();
function* onUnAuthorized() {
    history.push({
        pathname: '/index.html',
        search: '?path=login'
      });
}

function* onDoLogin(message) {
    try {
       
        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('response niet ok');
            yield put(receiveLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
        } else {
            console.log('response ok');
            const token = yield call(result, 'text');
            yield put(receiveToken(token));
            yield put(clearLoginError());
            const returnUrl = window.location;
            history.push(returnUrl);
        }
    } catch(e) {
        console.log(e);

        yield put(receiveLoginError(e.message));
    }
}


export default function* dashboardSaga() {
    yield takeLatest(DO_LOGIN, onDoLogin);
    yield takeLatest(UN_AUTHORIZED, onUnAuthorized);
}
