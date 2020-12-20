<template>
	<view class="wrap">
		<view>
			<view class="search-icon">
				<u-icon name="search"></u-icon>
			</view>
			<view>
				<input type="text" class="search" placeholder="输入进行检索" v-model="keyword" @input="search">
			</view>
		</view>

		<view v-if="show">
			<view v-for="item in garbages">
				<view class="card u-font-xl">
					<span>{{item.garbageName}}</span>
					<u-tag class="gc-tag" :text="item.gcName" :type="item.gcType"></u-tag>
				</view>
			</view>
			<view v-if="emty">
				<view class="card u-font-xl u-text-center">
					未找到相关数据
				</view>
			</view>
		</view>

		<view class="a" v-if="!show">
			<view>
				<view class="border-left"><span class="u-padding-10">热门搜索</span></view>
				<u-tag class="tag" v-for="item in tags" :text="item" type="success" />
			</view>
			<view>
				<view class="border-left"><span class="u-padding-10">Tips:</span></view>
				<view class="u-text-center">{{tips.garbageName}}属于：{{tips.gcName}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				garbages: [],
				showHead: false,
				emty: false,
				show: false,
				tags: [
					"苹果",
					"手机",
					"中性笔",
					"笔记本",
					"钱包",
					"洗面奶",
					"爽肤水",
					"笔记本电脑",
					"散热器"
				],
				tips: {}
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
							
							this.garbages = data;
							this.showHead = true;
							this.emty = false;
						} else {
							this.emty = true;
						}
					})
				} else {
					this.garbages = [];
					this.showHead = false;
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
	.tag {
		margin: 0.4375em;
		display: inline-flex;
	}

	.a>view {
		margin-top: 2em;
	}

	.card {
		width: 100%;
		height: 3em;
		margin-top: 1em;
		padding-left: 0.5em;
		line-height: 3em;
		background-color: white;
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.3);
	}

	.gc-tag {
		float: right;
		line-height: 3em;
		margin-right: 1em;
	}

	.search-icon {
		display: inline;
		float: left;
		height: 70rpx;
		line-height: 70rpx;
		background-color: #f2f2f2;
		padding-left: 10rpx;
		padding-right: 10rpx;
		border-top-left-radius: 7rpx;
		border-bottom-left-radius: 7rpx;
	}

	.search {
		width: 655rpx;
		height: 70rpx;
		background-color: #f2f2f2;
		text-indent: 15rpx;
		border-top-right-radius: 7rpx;
		border-bottom-right-radius: 7rpx;
	}
</style>
