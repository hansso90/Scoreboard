import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import Route from 'react-router';
import { ConnectedRouter } from 'react-router-redux';

import history from './history';
import store from './store';
import Login from './modules/Login';

const browserHistory = history();

//Listen to history POP action
browserHistory.listen((location) => {
    if(location.action && location.action === 'POP') {
        browserHistory.goBack();
    }
});


ReactDOM.render(
    <Provider store={store}>
        <ConnectedRouter history={history}>
            <div>
                <Route path="/login">
                    <Login returnUrl={this.props.match.params.redirectParam} />
                </Route>
            </div>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('reactRoot')
);
