import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route } from 'react-router';
import { createBrowserHistory } from 'history';

import store from './store';
import Login from './modules/Login';


window.onload = function () {
    ReactDOM.render(
        <Provider store={store}>
            <Router history={createBrowserHistory()}>
                <Route>
                    <Login />
                </Route>
            </Router>
        </Provider>,
        document.getElementById('reactRoot')
    );
};
