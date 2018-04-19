import createSaga from 'redux-saga';
import { createStore, applyMiddleware, compose } from 'redux';
import reduxThunk from 'redux-thunk';
// import { routerMiddleware } from 'react-router-redux';

import rootSaga from '../sagas/index';
import reducers from '../reducers';

const sagaMiddleware = createSaga(); // abc

const store = createStore(reducers, {}, compose(
    applyMiddleware(sagaMiddleware, reduxThunk), // logger must always be the last in the chain!
    window.devToolsExtension ? window.devToolsExtension() : f => f
));

sagaMiddleware.run(rootSaga);


export default store;

