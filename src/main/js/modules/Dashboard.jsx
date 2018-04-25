import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import ActivitiesOverview from '../components/ActivitiesOverview';
import Menu from '../components/Menu';

const Dashboard = (props) => {

    return (
        <div>
            <Menu />
            <div>
                <ActivitiesOverview {...props} />
            </div>
        </div>
    );
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        activityData: { ...state.activities }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Dashboard));

const { object } = proptypes;

Dashboard.propTypes = {
    userInputs: object.isRequired,
    actions: object.isRequired
};

Dashboard.defaultValues = {
    activityData: null
};

