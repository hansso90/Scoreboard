import React from 'react';
//import { withRouter } from 'react-router';
import { connect } from 'react-redux';
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
    return { actions: { ...actions } };
}
export default connect(mapStateToProps, mapDispatchToProps)(Login);

const { object } = proptypes;

Login.propTypes = {
    userInputs: object.isRequired
};

