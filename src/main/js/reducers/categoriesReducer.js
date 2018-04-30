import { assignIn } from 'lodash';
import { RECEIVE_CATEGORIES, RECEIVE_CATEGORY_ERROR } from '../actions/types';

const initalState = {
    categoryError: null,
    categories: null
};

export default (state = initalState, action) => {
    switch (action.type) {
        case RECEIVE_CATEGORY_ERROR:
            const obj = {
                categoryError: action.message
            };
            return assignIn({}, state, obj);
        case RECEIVE_CATEGORIES:
            return assignIn({}, state, { categories: action.categories, categoryError: null });

        default:
            return state;
    }
};
