export const GET_BORROW_LIST = 'GET_BORROW_LIST';

const failure = (msg) => {
	return { type: 'error', msg: msg };
};

const success = (msg, payload) => {
	return { type: 'success', msg: msg, payload: payload };
};

const getBorrowList = (type, payload) => {};
