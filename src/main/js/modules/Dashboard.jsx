import React from 'react';
import { find } from 'lodash/find';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import Menu from '../components/Menu';
import ProgressChapter from '../components/ProgressChapter';


const Dashboard = (props) => {
    const { activityData, userData, chapterData } = props;
    const { activityError, activities } = activityData;
    const waitingActivities = !activities && !activityError;
    const { chapterError, chapters } = chapterData;
    const waitingChapters = !chapters && !chapterError;

    const { userError, users } = userData;
    const waitingUsers = !users && !userError;


    if(chapterError || activityError) {
        return (
            <div>
                <Menu currentUser={userData.currentUser} />

                <span>{activityError}</span>
            </div>
        );
    }

    if(waitingActivities) {
        props.actions.requireActivities();
        return (
            <div>
                <Menu currentUser={userData.currentUser} />
                <span>Getting Dashboard Data</span>
            </div>
        );
    }

    if(waitingChapters) {
        props.actions.requireChapters();
        return (
            <div>
                <Menu currentUser={userData.currentUser} />
                <span>Getting Dashboard Data</span>
            </div>
        );
    }


    if(waitingUsers) {
        props.actions.requireUsers();
        return (
            <div>
                <Menu currentUser={userData.currentUser} />
                <span>Getting Dashboard Data</span>
            </div>
        );
    }

    const chapterMap = {};
    chapters.map((c, index) => {
        chapterMap[c.id] = {
            chapter: c
        };
    });

    activities.map((a, index) => {
        const chapter = {
            ...a.chapter,
            chapterColor: 'blue',
            stardust: a.stardust
        };
        return (<ProgressChapter chapter={chapter} activity={a} />);
    });
    return (
        <div style={{overflow: "hidden", 'max-height': "100%"}}>
            <Menu currentUser={userData.currentUser} />
            <div id="stars" />
            <div id="stars-back" />
            <div className="container-fluid">
                <div className="row progress-bars" />
            </div>
        </div>);
};
function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        activityData: { ...state.activities },
        userData: { ...state.users },
        chapterData: { ...state.chapters }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Dashboard));

const {
    shape, func, string, arrayOf
} = proptypes;

Dashboard.propTypes = {
    actions: shape({
        requireDashboardActivities: func.isRequired
    }).isRequired,
    activityData: shape({
        activityError: string,
        activities: arrayOf(shape({
        }))
    }).isRequired,
    userData: shape({
        currentUser: shape({})
    }).isRequired
};

Dashboard.defaultValues = {
    dashboardActivityData: null
};

