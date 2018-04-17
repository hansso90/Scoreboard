export const UPDATE_ELEMENT = 'ROCKSTARS/USERACTIONS/UPDATE_ELEMENT'; 



export default function updateElement(id, value){
    return {
        type: UPDATE_ELEMENT,
        id,
        value
    };
}