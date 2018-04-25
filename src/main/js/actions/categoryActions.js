import { REQUIRE_CATEGORIES, ADD_CATEGORY, RECEIVE_CATEGORIES, RECEIVE_CATEGORY_ERROR } from './types';

export function requireCategories() {
    return {
        type: REQUIRE_CATEGORIES
    };
}

export function addCategory(name) {
    return {
        type: ADD_CATEGORY,
        name
    };
}

export function onReceiveCategories(categories) {
    return {
        type: RECEIVE_CATEGORIES,
        categories
    };
}


export function receiveCategoryError(message) {
    return {
        type: RECEIVE_CATEGORY_ERROR,
        message
    };
}
