import React from 'react';
import DropdownWithLabel from './DropdownWithLabel';
import TextWithLabel from './TextWithLabel';
import Button from './Button';
import DayPickerInput from 'react-day-picker/DayPickerInput';
import MomentLocaleUtils, {
    formatDate,
    parseDate,
} from 'react-day-picker/moment';

import 'moment/locale/nl';


const AddActivity = (props) => {

    const users = props.users.map((u, i) => {
        return {
            key: i,
            name: u.name,
            value: i
        };
    });
    const userProps = {
        label: 'User',
        name: 'addActivity_user',
        actions: props.actions,
        userInputs: props.userInputs,
        options: users,
        defaultText: 'Select a user'
    };

    const categories = props.categories.map((c, i) => {
        return {
            key: i,
            name: c.name,
            value: c.id
        };
    });
    const categoryProps = {
        label: 'Category',
        name: 'addActivity_category',
        actions: props.actions,
        userInputs: props.userInputs,
        options: categories,
        defaultText: 'Select a category',
    };

    const chapters = props.chapters.map((c, i) => {
        return {
            key: i,
            name: c.name,
            value: c.id
        };
    });
    const chapterProps = {
        label: 'Chapter',
        name: 'addActivity_chapter',
        actions: props.actions,
        userInputs: props.userInputs,
        options: chapters,
        defaultText: 'Select a chapter'
    };

    const descriptionProps = {
        label: 'Description',
        name: 'addActivity_description',
        actions: props.actions,
        userInputs: props.userInputs
    };

    const stardustProps = {
        label: 'Stardust',
        name: 'addActivity_stardust',
        actions: props.actions,
        userInputs: props.userInputs
    };

    let date = new Date();
    function dayChange(d) {
        date = d;
    }

    const buttonProps = {
        label: 'Save',
        onClick: () => {
            props.actions.addActivity(
                props.users[props.userInputs[userProps.name]],
                props.userInputs[chapterProps.name],
                props.userInputs[categoryProps.name],
                props.userInputs[stardustProps.name],
                props.userInputs[descriptionProps.name],
                date
            );
        }
    };

    return (
        <div className="addActivity">
            <DropdownWithLabel {...userProps} />
            <DropdownWithLabel {...chapterProps} />
            <DropdownWithLabel {...categoryProps} />
            <TextWithLabel {...stardustProps} />
            <TextWithLabel {...descriptionProps} />
            <DayPickerInput
                value={date}
                onDayChange={dayChange}
                formatDate={formatDate}
                parseDate={parseDate}
                placeholder="Datum"
                dayPickerProps={{
                    locale: 'nl',
                    localeUtils: MomentLocaleUtils,
                }}
            />
            <Button {...buttonProps} />
        </div>
    );
};

export default AddActivity;
