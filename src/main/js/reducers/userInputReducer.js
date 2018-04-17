import {assignIn} from 'lodash';
import {UPDATE_ELEMENT} from '../actions/userInputActions';

const initalState = {

};

export default (state = initalState, action) => {
    switch (action.type) {
        case UPDATE_ELEMENT:
            
            let obj = {};
            obj[action.id] = action.value;
            return assignIn({},state,obj);

        default:
            return state;
    }
};
