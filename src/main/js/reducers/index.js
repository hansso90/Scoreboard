import { routerReducer } from 'react-router-redux';
import userInputReducer from './userInputReducer';

export default { router: routerReducer, ...userInputReducer };
