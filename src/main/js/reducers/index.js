import { combineReducers } from 'redux';
import userInputReducer from './userInputReducer';
import authorizationReducer from './authorizationReducer';
import activitiesReducer from './activitiesReducer';

export default combineReducers({ userInput: userInputReducer, authorization: authorizationReducer, activiteis:activitiesReducer });
