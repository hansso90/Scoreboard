import { createHistory, createHashHistory } from 'history';

let browserHistory = null;

if(history.pushState === undefined) { // < ie 10 fallback.
    browserHistory = createHashHistory;
} else {
    browserHistory = createHistory;
}

export default browserHistory;


/*
 * Small wrapper for our npm history plugin. It create a html5 interface by default.
 * If it detects that its on an older browser it creates a fallback.
 * This wrapper reduces code duplication if your component needs access to the history.
 */
