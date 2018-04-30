import React from 'react';
//import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';

class Logout extends React.Component {

    componentWillMount() {
        this.props.actions.doLogout();
    }


    render() {
        return (
            <div className="login">
                <span>Loging you out</span>
            </div>);
    }
}

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        authorization: { ...state.authorization }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(Logout);

const { object } = proptypes;

Logout.propTypes = {
    actions: object.isRequired
};

