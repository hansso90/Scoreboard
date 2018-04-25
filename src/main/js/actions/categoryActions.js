import { REQUIRE_CATEGORIES, ADD_CATEGORY, RECEIVE_CATEGORIES, RECEIVE_CATEGORY_ERROR, REMOVE_CATEGORY } from './types';

export function requireCategories() {
    return {
        type: REQUIRE_CATEGORIES
    };
}

export function addCategory(name, defaultStardust) {
    return {
        type: ADD_CATEGORY,
        name,
        defaultStardust
    };
}

export function removeCategory(id) {
    return {
        type: REMOVE_CATEGORY,
        id
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
