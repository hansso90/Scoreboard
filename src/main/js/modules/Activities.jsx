import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import actions from '../actions/index';
import Menu from '../components/Menu';
import AddActivity from "../components/AddActivity";


const Activities = (props) => {
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

    const categoryData = props.categoryData;
    const categoryError = categoryData.chapterError;
    const categories = categoryData.categories;
    const waitingCategories = !categories && !categoryError;
    if(waitingCategories) {
        props.actions.requireCategories();
    }

    const waiting = waitingUsers || waitingChapters || waitingCategories;

    return (
        <div>
            <Menu />
            <div>
                { waiting && (
                    <span>Getting data</span>
                )}
                { userError && <span>{userError}</span> }
                { chapterError && <span>{chapterError}</span> }
                { categoryError && <span>{categoryError}</span> }
                { !waiting && (
                    <div>
                        <AddActivity {...props} users={users} chapters={chapters} categories={categories}/>
                    </div>
                )}
            </div>
        </div>
    )
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        userData: { ...state.users },
        chapterData: { ...state.chapters },
        categoryData: { ...state.categories }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Activities));