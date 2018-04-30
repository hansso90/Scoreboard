import createSaga from 'redux-saga';
import { createStore, applyMiddleware, compose } from 'redux';
import reduxThunk from 'redux-thunk';
import rootSaga from './sagas/index';
import reducers from './reducers';
import { persistStore } from 'redux-persist';


const sagaMiddleware = createSaga(); // abc

const store = createStore(reducers, {}, compose(
    applyMiddleware(reduxThunk, sagaMiddleware), // logger must always be the last in the chain!
    window.devToolsExtension ? window.devToolsExtension() : f => f
));
const persistor = persistStore(store);
sagaMiddleware.run(rootSaga);


export default store;

