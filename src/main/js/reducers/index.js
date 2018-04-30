import storage from 'redux-persist/lib/storage'; // defaults to localStorage for web and AsyncStorage for react-native
import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';
import userInputReducer from './userInputReducer';
import authorizationReducer from './authorizationReducer';
import activitiesReducer from './activitiesReducer';
import dashboardActivitiesReducer from './dashboardActivitiesReducer';
import categoriesReducer from './categoriesReducer';
import chaptersReducer from './chaptersReducer';
import usersReducer from './usersReducer';


const persistConfig = {
    key: 'root',
    storage,
};


export default persistReducer(persistConfig, combineReducers({
    userInput: userInputReducer,
    authorization: authorizationReducer,
    activities: activitiesReducer,
    categories: categoriesReducer,
    chapters: chaptersReducer,
    users: usersReducer,
    dashboardActivities: dashboardActivitiesReducer
}));
