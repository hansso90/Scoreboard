import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import actions from '../actions/index';
import UsersOverview from '../components/UsersOverview';
import Menu from '../components/Menu';
import AddUser from '../components/AddUser';

const Users = (props) => {
    const userData = props.userData;
    const userError = userData.userError;
    const users = userData.users;
    const waitingUsers = !users && !userError;
    if(waitingUsers) {
        props.actions.requireUsers();
    }

    const chapterData = props.chapterData;
    const chapterError = chapterData.chapterError;
    const chapters = chapterData.chapters;
    const waitingChapters = !chapters && !chapterError;
    if(waitingChapters) {
        props.actions.requireChapters();
    }


    return (
        <div>
            <Menu />
            <div>
                { waitingChapters || waitingUsers && (
                    <span>Getting data</span>
                )}

                { chapterError && <span>{chapterError}</span>}
                { userError && <span>{userError}</span>}

                { !waitingChapters && !waitingUsers && (
                    <div>
                        <div>
                            <AddUser {...props} chapters={chapters} />
                        </div>
                        <div>
                            <UsersOverview {...props} users={users} />
                        </div>
                    </div>)}
            </div>
        </div>
    );
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        userData: { ...state.users },
        chapterData: { ...state.chapters }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Users));
