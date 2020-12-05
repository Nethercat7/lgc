import jwt from 'jsonwebtoken'

const storage = {
	set(k, v) {
		uni.setStorage({
			key: k,
			data: v
		})
	},
	get(k) {
		let data = '';
		uni.getStorage({
			key: k,
			success: function(resp) {
				data = resp.data
			}
		})
		return data
	},
	remove(k) {
		uni.removeStorage({
			key: k,
			success: function(res) {
			}
		})
	},
	getUser(k) {
		let user = {};
		uni.getStorage({
			key: k,
			success: function(resp) {
				user = jwt.decode(resp.data);
			}
		})
		return user
	}
}

export default storage
