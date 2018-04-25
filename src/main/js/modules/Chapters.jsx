import React from 'react';
import { withRouter } from 'react-router';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import actions from '../actions/index';
import ChaptersOverview from '../components/ChaptersOverview';
import Menu from '../components/Menu';
import AddChapter from '../components/AddChapter';

const Chapters = (props) => {
    return (
        <div>
            <Menu />
            <div>
                <AddChapter {...props} />
            </div>
            <div>
                <ChaptersOverview {...props} />
            </div>

        </div>
    );
};

function mapStateToProps(state) {
    return {
        userInputs: { ...state.userInput },
        chapterData: { ...state.chapters }
    };
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({ ...actions }, dispatch)
    };
}
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Chapters));
