import React from 'react';
import Category from './Category';

const CategoriesOverview = (props) => {
    const categoryData = props.categoryData;
    const error = categoryData.categoryError;
    const categories = categoryData.categories;
    if(!categories || error) {
        props.actions.requireCategories();
        return (
            <div>
                { error && <span>error</span>}
                { !error && <span>Getting categories</span>}
            </div>);
    }

    return (
        <div>
            <span> Received categories:</span>
            {categories.map((a) => {
                return (<Category category={a} actions={props.actions} />);
            })}

        </div>);
};
export default CategoriesOverview;
