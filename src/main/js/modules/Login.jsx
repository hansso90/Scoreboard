import React from 'react';
import URI from 'urijs';
import { Redirect } from 'react-router';
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

    const regularLoginButton = {
        label: 'Login',
        onClick: () => props.actions.doLogin(props.userInputs[userNameProps.name], props.userInputs[passwordProps.name])
    };

    const adLoginButton = {
        label: 'Login with Microsoft Credentials',
        onClick: () => {
            fetch('http://localhost:8080/ad/login').then((res) => {
                res.json().then((json) => {
                    const { url } = json;
                    window.location.href = url;
                });
            });
        }
    };

    if(!props.authorization.loginError && !props.authorization.token && !props.userData.currentUser) {
        const params = props.location.search;
        if(params.length > 0) {
            const pars = new URI(params).query(true);
            props.actions.doLogin(pars.username, pars.token);
        }
    }

    return (
        <div className="login">
            <TextWithLabel {...userNameProps} />
            <TextWithLabel {...passwordProps} />
            <Button {...regularLoginButton} />
            <Button {...adLoginButton} />
            {props.authorization.loginError &&
                <span>{props.authorization.loginError}</span>
            }
            {props.authorization.token && props.userData.currentUser && <Redirect to="/dashboard" />}
            {!props.authorization.loginError && (props.authorization.token || props.userData.currentUser) && !(!props.authorization.token && !props.userData.currentUser) &&
            <span>Loging you in</span>}
        </div>);
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        authorization: { ...state.authorization },
        userData: { ...state.users }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(Login);

const {
    objectOf, func, string, shape
} = proptypes;

Login.propTypes = {
    userInputs: objectOf(string).isRequired,
    authorization: shape({
        loginError: string,
        token: string
    }).isRequired,
    actions: shape({
        doLogin: func.isRequired
    }).isRequired,
    location: shape({
        search: string
    }).isRequired,
    userData: shape({
        currentUser: shape({
        }).isRequired
    }).isRequired
};

