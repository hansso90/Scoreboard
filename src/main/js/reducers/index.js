import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import userInputReducer from './userInputReducer';

export default combineReducers({ router: routerReducer, userInput: userInputReducer });
