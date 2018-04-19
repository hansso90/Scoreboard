import { assignIn } from 'lodash';
import { UPDATE_ELEMENT } from '../actions/types';

const initalState = {
};

export default (state = initalState, action) => {
    switch (action.type) {
        case UPDATE_ELEMENT:
            const obj = {};
            obj[action.id] = action.value;
            return assignIn({}, state, obj);
        default:
            return state;
    }
};
