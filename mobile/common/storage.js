import jwt from 'jsonwebtoken'

const storage = {
	set(k, v) {
		uni.setStorageSync(k, v)
	},
	get(k) {
		return uni.getStorageSync(k)
	},
	remove(k) {
		uni.removeStorageSync(k)
	},
	getUser() {
		return jwt.decode(uni.getStorageSync('token'));
	}
}

export default storage
