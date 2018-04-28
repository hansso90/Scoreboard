import React from 'react';
import propTypes from 'prop-types';
import LabelWithLabel from './LabelWithLabel';
import Button from './Button';

const Category = (props) => {
    const category = props.category;
    const nameProps = {
        label: 'Name',
        text: category.name
    };
    const stardustProps = {
        label: 'Stardust',
        text: `${category.defaultStardust}`
    };
    const buttonProps = {
        label: 'Remove',
        onClick: () => props.actions.removeCategory(category.id)
    };

    return (
        <div className="category">
            <LabelWithLabel {...nameProps} />
            <LabelWithLabel {...stardustProps} />
            <Button {...buttonProps} />
        </div>);
};

export default Category;
const { string, shape } = propTypes;

Category.propTypes = {
    category: shape({
        name: string,
        defaultStardust: string
    }).isRequired
};
