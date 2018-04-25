import { combineReducers } from 'redux';
import userInputReducer from './userInputReducer';
import authorizationReducer from './authorizationReducer';
import activitiesReducer from './activitiesReducer';
import categoriesReducer from './categoriesReducer';

export default combineReducers({
    userInput: userInputReducer, authorization: authorizationReducer, activities: activitiesReducer, categories: categoriesReducer
});
