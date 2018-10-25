const BASE_URL = 'http://www.rongtongzc.com';
// const BASE_URL = "http://111.75.158.23:8085/rtzc_pnc"

const BASE_ORDER_URL = 'http://xjliao.com:9098/rtzc';

const LOGIN_URL = BASE_URL + '/user/login';

const GET_USER_FUNDS_INFO_URL = BASE_URL + '/account/account_info';

const GET_BORROW_LIST_URL = BASE_URL + '/invest/get_borrow_list';

const GET_BORROW_INFO_URL = BASE_URL + '/invest/get_borrow_info';

const INVEST_URL = BASE_URL + '/invest/invest';

const CREATE_ORDER_URL = BASE_ORDER_URL + '/order/create';

const CONFIRM_ORDER_URL = BASE_ORDER_URL + '/order/pay';

module.exports = {
	LOGIN_URL: LOGIN_URL,
	GET_USER_FUNDS_INFO_URL: GET_USER_FUNDS_INFO_URL,
	GET_BORROW_LIST_URL: GET_BORROW_LIST_URL,
	GET_BORROW_INFO_URL: GET_BORROW_INFO_URL,
	INVEST_URL: INVEST_URL,
	CREATE_ORDER_URL: CREATE_ORDER_URL,
	CONFIRM_ORDER_URL: CONFIRM_ORDER_URL
};
