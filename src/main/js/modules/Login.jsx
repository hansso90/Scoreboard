import React from 'react';
import { withRouteData } from 'react-router';
import { connect } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions';
import TextWithLabel from '../components/TextWithLabel';

const Login = (props) => {
    const usernameBox = 'usernameBox';
    return (
        <div>
            <TextWithLabel label="UserName" text={props.userInputs[usernameBox]} name={usernameBox} />
        </div>);
};

function mapStateToProps(state) {
    return {
        ...state.userInput
    };
}

function mapDispatchToProps() {
    return { ...actions };
}
export default connect(mapStateToProps, mapDispatchToProps)(withRouteData(Login));

const { object } = proptypes;

Login.propTypes = {
    userInputs: object.isRequired
};
