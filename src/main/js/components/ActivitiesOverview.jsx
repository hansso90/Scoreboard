import React from 'react';

const ActivityOverview = (props) => {
    if(!props.activityData.activities || props.activityData.activityError) {
        props.actions.requireActivities();
        return (
            <div>
                { props.activityData.activityError && <span>props.activityData.activityError</span>}
                { !props.activityData.activityError && <span>Getting activities</span>}
            </div>);
    }
    const activities = props.activityData.activities;
    return (
        <div>
            <span> Received activities:</span>
            <span>{activities.map((a) => {
                <span>{a.id}</span>;
            })}
            </span>
        </div>);
};
export default ActivityOverview;
