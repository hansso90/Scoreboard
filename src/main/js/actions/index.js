import * as userInputActions from './userInputActions';
import * as activityActions from './activityActions';
import * as authorizationActions from './authorizationActions';

export default { ...userInputActions, ...activityActions, ...authorizationActions };
