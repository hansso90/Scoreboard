import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import actions from '../actions/index';
import ChaptersOverview from '../components/ChaptersOverview';
import Menu from '../components/Menu';
import AddChapter from '../components/AddChapter';

const Chapters = (props) => {
    const userData = props.userData;
    return (
        <div className="chapters">
            <Menu currentUser={userData.currentUser} />
            <div className="row">
                <div className="col-md-12 col-sm-24">
                    <AddChapter {...props} />
                </div>
                <div className="col-md-12 col-sm-24">
                    <ChaptersOverview {...props} />
                </div>
            </div>

        </div>
    );
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        chapterData: { ...state.chapters },
        userData: { ...state.users }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Chapters));
