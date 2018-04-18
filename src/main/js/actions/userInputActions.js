import { UPDATE_ELEMENT } from './types';

export default function updateElement(id, value) {
    return {
        type: UPDATE_ELEMENT,
        id,
        value
    };
}
