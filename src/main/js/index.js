import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, Redirect } from 'react-router';
import { createBrowserHistory } from 'history';

import store from './store';
import Login from './modules/Login';
import Dashboard from './modules/Dashboard';
import Categories from './modules/Categories';
import Chapters from './modules/Chapters';


window.onload = function () {
    ReactDOM.render(
        <Provider store={store}>
            <Router history={createBrowserHistory()}>
                <div>
                    <Route exact path="/" render={() => (<Redirect to="/login" />)} />
                    <Route exact path="/dashboard" component={Dashboard} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/categories" component={Categories} />
                    <Route exact path="/chapters" component={Chapters} />
                </div>
            </Router>
        </Provider>,
        document.getElementById('reactRoot')
    );
};

