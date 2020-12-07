import storage from './storage.js'

const baseUrl = 'http://127.0.0.1:8080'
const request = (url = '', data = {}, type = '') => {
	return new Promise((resolve, reject) => {
		uni.request({
			method: type,
			url: baseUrl + url,
			data: data,
			header: {token:storage.get('token')},
			dataType: 'json',
		}).then((response) => {
			setTimeout(function() {
				uni.hideLoading();
			}, 200);
			let [error, res] = response;
			resolve(res.data);
		}).catch(error => {
			let [err, res] = error;
			reject(err)
		})
	});
}
export default request
