import * as userInputActions from './userInputActions';
import * as activityActions from './activityActions';
import * as categoryActions from './categoryActions';
import * as chapterActions from './chapterActions';
import * as userActions from './userActions';
import * as authorizationActions from './authorizationActions';

export default {
    ...userInputActions, ...activityActions, ...authorizationActions, ...categoryActions, ...chapterActions, ...userActions
};
