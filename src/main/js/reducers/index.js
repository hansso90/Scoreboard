import { combineReducers } from 'redux';
import userInputReducer from './userInputReducer';

export default combineReducers({ userInput: userInputReducer });
