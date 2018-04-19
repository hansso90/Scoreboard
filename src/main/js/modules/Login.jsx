import React from 'react';
//import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import TextWithLabel from '../components/TextWithLabel';

const Login = (props) => {
    const userNameProps = {
        label: 'User name:',
        name: 'userNameBox',
        ...props
    };
    return (
        <div>
            <TextWithLabel {...userNameProps} />
        </div>);
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(Login);

const { object } = proptypes;

Login.propTypes = {
    userInputs: object.isRequired
};

