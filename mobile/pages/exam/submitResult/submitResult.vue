<template>
	<view>
		<u-toast ref="uToast" />
		<!-- 头部 -->
		<view class="result-header">
			<view class="result-score">{{score}}分</view>
			<view class="result-integral">
				<span v-if="answer.length<5">
					恭喜你一共答对了{{5-answer.length}}题，获得{{integral}}点积分
				</span>
				<span v-if="answer.length==5">
					一题都没答对哦，不要灰心下次继续努力！
				</span>
			</view>
		</view>

		<!-- 题目及正确答案 -->
		<view class="result-data">
			<view class="u-border-bottom result" v-for="item in garbages" :key="garbageId">
				<span>{{item.garbageName}}</span>
				<u-tag :text="item.gcName" :type="item.gcType"></u-tag>
			</view>
		</view>

		<!-- 按钮 -->
		<view class="result-btn">
			<view>
				<button class="btn" @click="$jump.redirect('/pages/exam/randomExam/randomExam')">再答一次</button>
			</view>
			<view>
				<button class="btn" @click="$jump.switchTab('/pages/index/index')">返回首页</button>
			</view>
		</view>
	</view>
</template>

<script>
	import storage from '@/common/storage.js';

	export default {
		data() {
			return {
				score: 90,
				answer: [],
				count: 0,
				integral: 4,
				garbages: []
			}
		},
		methods: {
			calculateScore(num) {
				let total = 100; //总分
				let single = 100 / num; //总分÷题目数量=平均每题的分数
				for (let i = 0; i < this.answer.length; i++) {
					total = total - single
				}
				this.score = total;
				this.integral = num - this.answer.length;
				//更新用户积分，仅限第一次刷新
				//this.updIntegral();
			},
			updIntegral() {
				this.$u.api.updIntegral({
					integral: this.integral,
					id: storage.getUser('token').userId
				}).then(resp => {
					if (resp.data.code != 1) {
						this.$refs.uToast.show({
							title: resp.data.msg,
							type: resp.data.type,
							position: 'top'
						})
					}
				})
			},
			getGarbages(data) {
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
				this.garbages = data
			}
		},
		onLoad(option) {
			//错题
			this.answer = JSON.parse(decodeURIComponent(option.answer));
			this.count = this.answer.length;
			//一共有多少题，按照题目数量平均分配分数。
			this.calculateScore(5);
			//题目
			this.getGarbages(JSON.parse(decodeURIComponent(option.garbages)));
		},
		onBackPress() {
			this.redirectTo("/pages/exam/exam");
		}
	}
</script>

<style lang="scss">
	.result-header {
		height: 250rpx;
		background-color: #19be6b;
	}

	.result-score {
		color: #FFFFFF;
		font-size: 50rpx;
		text-align: center;
		height: 130rpx;
		line-height: 130rpx;
	}

	.result-integral {
		width: 70%;
		margin: auto;
		height: 50rpx;
		line-height: 50rpx;
		color: #FFFFFF;
		text-align: center;
		background-color: rgba($color: #000000, $alpha: .3);
		border-radius: 12rpx;
	}

	.result-data {
		width: 90%;
		margin: auto;
		border-radius: 30rpx;
		background-color: #FFFFFF;
		position: relative;
		bottom: 30rpx;
	}

	.result {
		padding: 30rpx;
	}

	.result u-tag {
		float: right;
	}

	.result-btn {
		width: 90%;
		margin: auto;
	}

	.result-btn view {
		margin-bottom: 30rpx;
	}

	.btn {
		color: #FFFFFF;
		background-color: $u-type-success;
	}
</style>
