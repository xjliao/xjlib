import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import Home from './Home';

const mapStateToProps = (state) => ({
	data: state.data
});

const mapDispatchToProps = (dispatch) => {
	return bindActionCreators(
		{
			getBorrowList
		},
		dispatch
	);
};

export default connect(mapStateToProps, mapDispatchToProps)(Home);
