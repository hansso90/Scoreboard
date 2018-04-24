import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import TextWithLabel from '../components/TextWithLabel';
import Button from '../components/Button';

const Dashboard = (props) => {
    if(!props.activityData.activities || props.activityData.activityError){
        props.actions.requireActivities();
        return (<div>
            { props.activityData.activityError &&<span>props.activityData.activityError</span>}
            { !props.activityData.activityError &&<span>Getting activities</span>}
        </div>);
    }
    return (
        <div>
            <span>{props.activities}</span>
        </div>);
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
    activityData:null
}

