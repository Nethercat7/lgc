import storage from '@/common/storage.js';

//请求拦截器
const install = (Vue, vm) => {
	//配置参数
	Vue.prototype.$u.http.setConfig({
		baseUrl: 'http://127.0.0.1:8080',
		loadingText: '正在加载...',
		loadingTime: 800,
		originalData: true,
		loadingMask: true,
		header: {
			'content-type': 'application/json;charset=UTF-8'
		}
	});

	Vue.prototype.$u.http.interceptor.request = (config) => {
		//引用token
		const token = storage.get('token');
		if (token) {
			config.header.token = token;
		}
		return config;
	}

	Vue.prototype.$u.http.interceptor.response = (res) => {
		if (res.statusCode == 200) {
			return res;
		} else if (res.statusCode == 400) {
			vm.$u.toast('错误请求');
		} else if (res.statusCode == 401) {
			vm.$u.toast('未授权，请重新登录');
			setTimeout(() => {
				vm.$u.route('/pages/user/login')
			}, 1500)
			return false;
		} else if (res.statusCode == 403) {
			vm.$u.toast('拒绝访问');
			return false;
		} else if (res.statusCode == 404) {
			vm.$u.toast('请求错误,未找到该资源');
			return false;
		} else if (res.statusCode == 405) {
			vm.$u.toast('请求方法未允许');
			return false;
		} else if (res.statusCode == 408) {
			vm.$u.toast('请求超时');
			return false;
		} else if (res.statusCode == 500) {
			vm.$u.toast('服务器端出错');
			return false;
		} else if (res.statusCode == 501) {
			vm.$u.toast('网络未实现');
			return false;
		} else if (res.statusCode == 502) {
			vm.$u.toast('网络错误');
			return false;
		} else if (res.statusCode == 503) {
			vm.$u.toast('服务不可用');
			return false;
		} else if (res.statusCode == 504) {
			vm.$u.toast('网络超时');
			return false;
		} else if (res.statusCode == 505) {
			vm.$u.toast('http版本不支持该请求');
			return false;
		} else {
			return false;
		}
	}
}

export default {
	install
}
