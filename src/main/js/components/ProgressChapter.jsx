import React from 'react';
import propTypes from 'prop-types';
import Chapter from './Chapter';

class ProgressChapter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            expanded: false
        };
        this.onExpand = this.onExpand.bind(this);
    }

    onExpand() {
        this.setState({ expanded: !this.state.expanded });
    }

    render() {
        const { chapter, activity } = this.props;

        return (
            <div className="col-sm-24" onClick={this.onExpand}>
                <div className={`progress-bar stripes ${chapter.chapterColor}`}>
                    <span style={{ width: `${chapter.stardust / 4}%` }} className="stardustText" />
                    <span className="rockhand">
                        <Chapter {...this.props} />
                        <div className={`pointer ${chapter.chapterColor}`}>🤘</div>
                    </span>
                </div>
                {this.state.expanded &&
                <div>
                    <span>Uitgeklapt</span>
                </div>}
            </div>
        );
    }
}
const { string, shape, number } = propTypes;

ProgressChapter.propTypes = {
    chapter: shape({
        chapterName: string,
        chapterColor: string,
        stardust: number
    }).isRequired
};

ProgressChapter.DefaultValue = {
    chapter: shape({
        chapterName: '',
        chapterColor: '',
        stardust: 0
    })
};

export default ProgressChapter;
