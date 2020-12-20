<template>
	<view>
		<view class="search-bar">
			<view>
				<view class="search-icon">
					<u-icon name="search"></u-icon>
				</view>
				<view>
					<input type="text" class="search" placeholder="输入进行检索" v-model="keyword" @input="search">
				</view>
				<u-row class="search-btn">
					<u-col span="6">
						<button @click="showModal=true">
							<u-icon name="camera" style="margin-right: 10rpx;"></u-icon>拍照搜索
						</button>
					</u-col>
					<u-col span="6">
						<button @click="showModal=true">
							<u-icon name="mic" style="margin-right: 10rpx;"></u-icon>语音搜索
						</button>
					</u-col>
				</u-row>
			</view>
		</view>

		<view style="padding:30rpx;">
			<view v-if="show">
				<view v-for="item in garbages" class="card u-font-xl">
					<span>{{item.garbageName}}</span>
					<u-tag class="gc-tag" :text="item.gcName" :type="item.gcType"></u-tag>
				</view>
				<view v-if="emty">
					<view class="card u-font-xl u-text-center">
						未找到相关数据
					</view>
				</view>
			</view>

			<view v-if="!show">
				<view>
					<view class="border-left"><span class="u-padding-10">Tips:</span></view>
					<view class="u-text-center">{{tips.garbageName}}属于：{{tips.gcName}}</view>
				</view>
			</view>
		</view>
		<u-modal v-model="showModal" content="此功能暂未开放"></u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				garbages: [],
				emty: false,
				show: false,
				tips: {},
				showModal: false
			};
		},
		methods: {
			search() {
				if (this.keyword != '') {
					this.show = true;
					this.$u.api.getGarbages({
						name: this.keyword
					}).then(resp => {
						let data = resp.data.obj;
						if (data.length > 0) {
							/* tag样式转换 */
							for (let i = 0; i < data.length; i++) {
								if (data[i].gcType === 'kitchen') {
									data[i].gcType = 'success'
								} else if (data[i].gcType === 'hazardous') {
									data[i].gcType = 'error'
								} else if (data[i].gcType === 'other') {
									data[i].gcType = 'info'
								} else if (data[i].gcType === 'recyclable') {
									data[i].gcType = 'primary'
								}
							}
							this.emty = false;
						} else {
							this.emty = true;
						}
						this.garbages = data;
					})
				} else {
					this.garbages = [];
					this.emty = false;
					this.show = false;
				}
			},
			getTips() {
				this.$u.api.getGarbages({
					num: 1
				}).then(resp => {
					this.tips = resp.data.obj[0];
				})
			}
		},
		onShow() {
			this.getTips();
		}
	}
</script>

<style lang="scss">
	.card {
		width: 100%;
		height: 3em;
		padding-left: 0.5em;
		margin-bottom: 1em;
		line-height: 3em;
		background-color: white;
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.3);
	}

	.gc-tag {
		float: right;
		position: relative;
		bottom: 5rpx;
		right: 30rpx;
	}

	.search-icon {
		display: inline;
		float: left;
		height: 70rpx;
		line-height: 70rpx;
		background-color: #FFFFFF;
		padding-left: 10rpx;
		padding-right: 10rpx;
		border-top-left-radius: 7rpx;
		border-bottom-left-radius: 7rpx;
	}

	.search {
		width: 648rpx;
		height: 70rpx;
		background-color: #FFFFFF;
		text-indent: 15rpx;
		border-top-right-radius: 7rpx;
		border-bottom-right-radius: 7rpx;
	}

	.search-btn button {
		color: #FFFFFF;
		background-color: rgba($color: #ffffff, $alpha: .1);
	}

	.search-btn button:active {
		color: #FFFFFF;
		background-color: rgba($color: #ffffff, $alpha: .5);
	}

	.search-bar {
		height: 250rpx;
		background-color: $u-type-success;
	}

	.search-bar>view {
		padding: 30rpx;
	}
</style>
