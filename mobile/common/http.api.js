// /common/http.api.js
const install = (Vue, vm) => {
	//获取文章
	let getPosts = (params = {}) => vm.$u.get('/postsMgt/getPosts', params);

	//获取垃圾分类
	let getGarbages = (params = {}) => vm.$u.get('/garbage/getGarbages', params);

	//获取垃圾分类目录
	let getGarbageCategories = (params = {}) => vm.$u.get('/garbage/getCategories', params);

	//更新用户积分
	let updIntegral = (params = {}) => vm.$u.get('/user/updIntegral', params);

	//通过用户名获取用户
	let getUser = (params = {}) => vm.$u.get('/user/getUserByName', params);

	//更新用户信息
	let updUser = (params = {}) => vm.$u.post('/user/upd', params);
	
	//获取所有用户
	let getUsers = (params = {}) => vm.$u.get('/userMgt/getUsers', params);
	
	//获取指定用户排名
	let getUserRate = (params = {}) => vm.$u.get('/userMgt/getUserRate', params);

	vm.$u.api = {
		getPosts,
		getGarbages,
		getGarbageCategories,
		updIntegral,
		getUser,
		updUser,
		getUsers,
		getUserRate
	};
}

export default {
	install
}
