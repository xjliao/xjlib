import { request } from './client';
import { GET_BORROW_LIST_URL } from './url';

export const getBorrowList = (params, success, failure) => {
	request(GET_BORROW_LIST_URL, params, success, failure);
};
