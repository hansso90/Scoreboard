import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import Menu from '../components/Menu';
import ProgressChapter from '../components/ProgressChapter';


const Dashboard = (props) => {
    const dashboardActivityData = props.dashboardActivityData;
    const dashboardActivityError = dashboardActivityData.dashboardActivityError;
    const dashboardActivities = dashboardActivityData.dashboardActivities;
    const waitingActivities = !dashboardActivities && !dashboardActivityError;
    if(waitingActivities) {
        props.actions.requireDashboardActivities();
        return (
            <div>
                <Menu />
                <span>Getting Dashboard Data</span>
            </div>
        );
    }

    if(dashboardActivityError) {
        return (
            <div>
                <Menu />
                <span>{dashboardActivityError}</span>
            </div>
        );
    }


    const chaptersHTML = dashboardActivities.chapters.map((c, index) =>
        (<ProgressChapter chapter={c} />)
    );

    return (
        <div>
            <Menu />
            <div id="stars" />
            <div id="stars-back" />
            <div className="container-fluid">
                <div className="row progress-bars">
                    {chaptersHTML}
                </div>
            </div>
        </div>);
};
function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        dashboardActivityData: { ...state.dashboardActivities }
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
    dashboardActivityData: null
};

