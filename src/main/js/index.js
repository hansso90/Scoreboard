import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import Route from 'react-router';
import { ConnectedRouter } from 'react-router-redux';

// import history from './history';
import store from './store';
import Login from './modules/Login';

// const browserHistory = history();

// //Listen to history POP action
// browserHistory.listen((location) => {
//     if(location.action && location.action === 'POP') {
//         browserHistory.goBack();
//     }
// });

window.onload = function () {
    ReactDOM.render(
        <Provider store={store}>
            <Login />
        </Provider>,
        document.getElementById('reactRoot')
    );
};
