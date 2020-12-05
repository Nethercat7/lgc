<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	@import "uview-ui/index.scss";

	.wrap {
		padding: 24rpx;
	}

	.u-row {
		margin: 40rpx 0;
	}
	
	//标题左边的颜色
	.border-left {
		color: $u-main-color;
		border-left: 0.1875em solid rgb(25, 190, 107);
		padding-right: 0.625em;
	}
</style>
