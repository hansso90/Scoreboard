import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import actions from '../actions/index';
import CategoriesOverview from '../components/CategoriesOverview';
import Menu from '../components/Menu';
import AddCategory from '../components/AddCategory';

const Categories = (props) => {
    return (
        <div>
            <Menu />
            <div>
                <AddCategory {...props} />
            </div>
            <div>
                <CategoriesOverview {...props} />
            </div>

        </div>
    );
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        categoryData: { ...state.categories }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Categories));
