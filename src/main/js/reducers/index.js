import { combineReducers } from 'redux';
import userInputReducer from './userInputReducer';
import authorizationReducer from './authorizationReducer';

export default combineReducers({ userInput: userInputReducer, authorization: authorizationReducer });
