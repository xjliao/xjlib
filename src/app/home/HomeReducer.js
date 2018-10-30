import { getBorrowList } from '../../net/api';

const initState = {
	type: '',
	payload: ''
};

const homeReducer = (state = initialState, action) => {
	const payload = action.payload;

	switch (action.type) {
		case getBorrowList:
			return [ ...state, action.payload ];
		default:
			return state;
	}
};

export default homeReducer;
