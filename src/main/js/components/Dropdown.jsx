import React from 'react';
import isObject from 'lodash/isObject';
import find from 'lodash/find';

const Dropdown = (props) => {


    const handleChange = (e) => {
        const val = e.target.value;
        props.actions.updateElement(name, val);
    };

    const _selectedOption = () => {
        const {
            defaultText, options, userInputs, name
        } = props;
        const selectedValue = userInputs[name];

        if(selectedValue) {
            const selectedOption = find(options, (o) => { return `${selectedOption}` === `${o.value}`; });

            return selectedOption ? selectedOption.name : defaultText;
        }

        return defaultText;
    };

    const _selectedValue = () => {
        const { userInputs, name, options } = props;
        const selectedValue = userInputs[name];
        if(selectedValue) {
            const selectedOption = find(options, (o) => { return `${o.value}` === `${selectedValue}`; });

            return selectedOption ? selectedOption.value : '';
        }

        return '';
    };
    const { options, name } = props;

    const htmlOptions = options.map((option, index) => {
        let optionValue = option;
        let optionName = option;

        if(isObject(option)) { //If option is an option object we read out its name and value properties.
            optionValue = option.value;
            optionName = option.name;
        }


        return <option key={index} value={optionValue}>{optionName}</option>;
    }); //A list of options.

    let firstElement = null;
    const { defaultText } = props;
    if(defaultText !== undefined && defaultText.length > 0) {
        firstElement = <option key="defaultText" value="" disabled>{defaultText}</option>;
    }

    //Add first element to first place in array.
    htmlOptions.unshift(firstElement);


    return (
        <div >
            <select
                onChange={handleChange}
                value={_selectedValue()}
                name={name}
            >
                {htmlOptions}
            </select>
            <p>{ _selectedOption() }</p>
        </div>
    );
};

export default Dropdown;
