import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route } from 'react-router';
import { createBrowserHistory } from 'history';

import store from './store';
import Login from './modules/Login';
import Dashboard from './modules/Dashboard';


window.onload = function () {
    ReactDOM.render(
        <Provider store={store}>
            <Router history={createBrowserHistory()}>
                <div>
                <Route exact path='/' component={Dashboard}/>
                    <Route exact path='/index.html?path=dashboard' component={Dashboard}/>
                    <Route exact path='/index.html?path=login' component={Login}/>
                </div>
            </Router>
        </Provider>,
        document.getElementById('reactRoot')
    );  
};


