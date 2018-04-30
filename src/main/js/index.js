import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, Redirect } from 'react-router';
import { createBrowserHistory } from 'history';

import store from './store';
import Login from './modules/Login';
import Logout from './modules/Logout';
import Dashboard from './modules/Dashboard';
import Categories from './modules/Categories';
import Chapters from './modules/Chapters';
import Users from './modules/Users';
import Activities from './modules/Activities';

window.onload = function () {
    ReactDOM.render(
        <Provider store={store}>
            <Router history={createBrowserHistory()}>
                <div>
                    <Route exact path="/" render={() => (<Redirect to="/dashboard" />)} />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/logout" component={Logout} />
                    <Route exact path="/categories" component={Categories} />
                    <Route exact path="/chapters" component={Chapters} />
                    <Route exact path="/users" component={Users} />
                    <Route exact path="/activities" component={Activities} />
                </div>
            </Router>
        </Provider>,
        document.getElementById('reactRoot')
    );
};

