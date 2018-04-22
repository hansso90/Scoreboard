
import { takeLatest, call, put } from 'redux-saga/effects';
import actions from '../actions/index';
import { login } from '../services/authorizationService';
import { getActivities, getActivityById } from '../services/activityService';
import { UN_AUTHORIZED, DO_LOGIN } from '../actions/types';

const { clearLoginError, receiveLoginError } = actions;

function* onUnAuthorized() {
    //goto login with the current url as return url
}

function* onDoLogin(message) {
    try {
        localStorage.removeItem('token');
        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('response niet ok');
            yield put(receiveLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
        } else {
            console.log('response ok');
            getActivities().then(o => o.body.getReader().read().then(obj => console.log(new TextDecoder().decode(obj.value))));
            yield put(clearLoginError());
        //goto message.returnurl
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
