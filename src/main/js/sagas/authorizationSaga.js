
import { takeLatest, call, put } from 'redux-saga/effects';
import { clearLoginError, receivLoginError } from '../actions/index';
import { login } from '../services/authorizationService';
import { UN_AUTHORIZED, DO_LOGIN } from '../actions/types';


function* onUnAuthorized() {
    //goto login with the current url as return url
}

function* onDoLogin(message) {
    try {
        const result = yield call(login, message.username, message.password);
        if(!result.ok) {
            console.log('response niet ok');
            yield put(receivLoginError('De gebruikersnaam en het wachtwoord komen niet overeen'));
        } else {
            yield put(clearLoginError());
        //goto message.returnurl
        }
    } catch(e) {
        console.log(e);

        yield put(receivLoginError(e.message));
    }
}


export default function* dashboardSaga() {
    yield takeLatest(DO_LOGIN, onDoLogin);
    yield takeLatest(UN_AUTHORIZED, onUnAuthorized);
}
