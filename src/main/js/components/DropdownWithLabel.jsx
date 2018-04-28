import React from 'react';
import Label from './Label';
import Dropdown from './DropDown';


const DropdownWithLabel = (props) => {
    return (
        <div className="dropdownWithLabel">
            <Label label={props.label} />
            <Dropdown {...props} />
        </div>);
};


export default DropdownWithLabel;
