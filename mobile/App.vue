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
	
	page{
		background-color: $u-bg-color;;
	}

	.wrap {
		padding: 24rpx;
	}

	.u-row {
		margin: 40rpx 0;
	}

	//标题左边的颜色
	.border-left {
		color: $u-main-color;
		border-left: 10rpx solid #19be6b;
		padding-right: 0.625em;
		margin-bottom: 0.9375em;
		height: 2em;
		line-height: 2em;
	}

	.shadow {
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.7);
	}

	button[type='kitchen'] {
		background-color: #19be6b;
	}

	button[type='hazardous'] {
		background-color: #fa3534;
	}

	button[type='other'] {
		background-color: #909399;
	}

	button[type='recyclable'] {
		background-color: #2979ff;
	}
	
	button[type='kitchen']:active{
		background-color: rgba($color:#19be6b,$alpha:0.5);
	}
	
	button[type='hazardous']:active{
		background-color: rgba($color:#fa3534,$alpha:0.5);
	}
	
	button[type='other']:active{
		background-color: rgba($color:#909399,$alpha:0.5);
	}
	
	button[type='recyclable']:active{
		background-color: rgba($color:#2979ff,$alpha:0.5);
	}

	.float-left {
		float: left;
		padding-left: 30rpx;
	}

	.float-right {
		float: right;
		padding-right: 30rpx;
	}
</style>
