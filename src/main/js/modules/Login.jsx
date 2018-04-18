import React from 'react';
import { withRouteData } from 'react-router';
import { connect } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions';
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
