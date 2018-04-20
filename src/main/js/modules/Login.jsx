import React from 'react';
//import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import proptypes from 'prop-types';
import actions from '../actions/index';
import TextWithLabel from '../components/TextWithLabel';
import Button from '../components/Button';

const Login = (props) => {
    const userNameProps = {
        label: 'User name:',
        name: 'userNameBox',
        ...props
    };


    const passwordProps = {
        label: 'Password:',
        name: 'passwordBox',
        blur: true,
        ...props
    };

    const buttonProps = {
        label: 'Login',
        onClick: () => props.actions.doLogin(props.userInputs[userNameProps.name], props.userInputs[passwordProps.name])
    };

    return (
        <div>
            <TextWithLabel {...userNameProps} />
            <TextWithLabel {...passwordProps} />
            <Button {...buttonProps} />
            {props.authorization.loginError &&
                <span>{props.authorization.loginError}</span>
            }
        </div>);
};

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
export default connect(mapStateToProps, mapDispatchToProps)(Login);

const { object } = proptypes;

Login.propTypes = {
    userInputs: object.isRequired,
    authorization: object.isRequired,
    actions: object.isRequired
};

