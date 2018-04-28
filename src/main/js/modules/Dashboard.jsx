import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';



const Dashboard = (props) => {
    const chapters = [
        {color: "blue", name: "NORTH"},
        {color: "orange", name: "EAST"},
        {color: "pink", name: "WEST"},
        {color: "green", name: "SOUTH"}
    ];

    const chaptersHTML = chapters.map(obj =>
            <div className="col-sm-24">
                <div className={"progress-bar stripes " + obj.color }>
                    <span style={{width: '30%'}} className="stardustText"></span>
                    <span className="rockhand">
                            <div className="label">{obj.name}</div>
                            <div className="pointer">🤘</div>
                    </span>
                </div>
            </div>
        );

    const activityData = props.activityData;
    const activityError = activityData.activityError;
    const activities = activityData.activities;
    const waitingActivities = !activities && !activityError;
    if (waitingActivities) {
        props.actions.requireActivities();
    }


    return (
        <div>
            <div id='stars'></div>
            <div id='stars-back'></div>
            <div className="container-fluid">
                <div className="row progress-bars">
                    {chaptersHTML}
                </div>
            </div>
        </div>);
};
function mapStateToProps(state) {
    return {
    userInputs: {...state.userInput},
    activityData: {...state.activities}
};
}

function mapDispatchToProps(dispatch) {
    return {
    actions: bindActionCreators({...actions}, dispatch)
};
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Dashboard));

const {object} = proptypes;

Dashboard.propTypes = {
    userInputs: object.isRequired,
    actions: object.isRequired
};

Dashboard.defaultValues = {
    activityData: null
};

