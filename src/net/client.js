import axios from 'axios';

const request = (url, params, success, failure, message) => {
	requestLoading(url, params, success, failure, message);
};

const requestLoading = (url, params, success, failure, message) => {
	axios
		.post(url, params, {
			timeout: 60000,
			headers: {
				'Content-Type': 'application/json'
			},
			responseType: 'json'
		})
		.then((response) => {
			console.log(response);
			success(response.data);
		})
		.catch((error) => {
			console.log(error);
			failure(error);
		});
};

export { request, requestLoading };
