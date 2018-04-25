import React from 'react';

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
                return (
                    <div>
                        <div>
                            <span>Id: </span>                    <span>{a.id}</span>
                        </div>
                        <div>
                            <span>Name: </span>                    <span>{a.name}</span>
                        </div>
                    </div>);
            })}

        </div>);
};
export default CategoriesOverview;
