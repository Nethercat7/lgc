const jump = {
	navigate(url) {
		uni.navigateTo({
			url: url
		})
	},
	redirect(url){
		uni.redirectTo({
			url:url
		})
	},
	reLanuch(url){
		uni.reLaunch({
			url:url
		})
	},
	switchTab(url){
		uni.switchTab({
			url:url
		})
	},
	navigateBack(url){
		uni.navigateBack({
			url:url
		})
	},
	preloadPage(url){
		uni.preloadPage({
			url:url
		})
	}
}

export default jump
