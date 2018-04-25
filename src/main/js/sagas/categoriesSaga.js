
import { takeLatest, call, put, select } from 'redux-saga/effects';
import actions from '../actions/index';
import { getCategories, createCategory, removeCategory } from '../services/categoryService';
import { REQUIRE_CATEGORIES, ADD_CATEGORY, REMOVE_CATEGORY } from '../actions/types';

export const getToken = state => state.authorization.token;
const { onReceiveCategories, receiveCategoryError, unAuthorized } = actions;

function* getAllCategories() {
    try {
        const token = yield select(getToken);
        const categoriesResponse = yield call(getCategories, token);

        if(categoriesResponse.ok) {
            const categories = yield call([categoriesResponse, 'json']);
            yield put(onReceiveCategories(categories));
            return;
        }

        if(categoriesResponse.status === 403 || categoriesResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([categoriesResponse, 'text']);
            yield put(receiveCategoryError(message));
        }
    } catch(e) {
        yield put(receiveCategoryError(e.message));
        yield put(unAuthorized());
    }
}

function* onAddCategory(msg) {
    try {
        const { name, defaultStardust } = msg;
        const token = yield select(getToken);
        const categoriesResponse = yield call(createCategory, token, name, defaultStardust);

        if(categoriesResponse.ok) {
            yield call(getAllCategories);
            return;
        }

        if(categoriesResponse.status === 403 || categoriesResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([categoriesResponse, 'text']);
            yield put(receiveCategoryError(message));
        }
    } catch(e) {
        yield put(receiveCategoryError(e.message));
        yield put(unAuthorized());
    }
}


function* onRemoveCategory(msg) {
    try {
        const { id } = msg;
        const token = yield select(getToken);
        const categoriesResponse = yield call(removeCategory, token, id);

        if(categoriesResponse.ok) {
            yield call(getAllCategories);
            return;
        }

        if(categoriesResponse.status === 403 || categoriesResponse.status === 401) { //unauthorized
            yield put(unAuthorized());
        } else {
            const message = yield call([categoriesResponse, 'text']);
            yield put(receiveCategoryError(message));
        }
    } catch(e) {
        yield put(receiveCategoryError(e.message));
        yield put(unAuthorized());
    }
}


export default function* categoriesSaga() {
    yield takeLatest(REQUIRE_CATEGORIES, getAllCategories);
    yield takeLatest(ADD_CATEGORY, onAddCategory);
    yield takeLatest(REMOVE_CATEGORY, onRemoveCategory);
}
