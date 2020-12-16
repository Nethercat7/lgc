<template>
	<view class="wrap">
		<u-toast ref="uToast" />
		<u-row gutter="16">
			<u-col span="12">
				<view class="u-text-center question">
					<view>
						宁的分数
					</view>
					<view>
						{{score}}
					</view>
					<view>
						获得{{integral}}点积分
					</view>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" v-if="answer.length >0">
			<u-col span="12">
				<view class="border-left">
					<span class="u-padding-10">错题</span>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<view class="wrong-answer" v-for="item in answer">
					<view>
						{{item.garbageName}}属于：{{item.gcName}}
					</view>
					<view>
						宁的答案为：{{item.errAnswer}}
					</view>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<u-button :custom-style="style" @click="redirectTo('/pages/exam/randomExam/randomExam')">再答一次</u-button>
				<u-button @click="redirectTo('/pages/index/index')">返回首页</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	import storage from '../../../common/storage.js';

	export default {
		data() {
			return {
				score: 0,
				answer: [],
				count: 0,
				integral: 0,
				style: {
					'margin-bottom': '0.625em'
				}
			}
		},
		methods: {
			redirectTo(url) {
				storage.remove('isLoad');
				uni.redirectTo({
					url: url
				})
			},
			calculateScore(num) {
				let total = 100; //总分
				let single = 100 / num; //总分÷题目数量=平均每题的分数
				for (let i = 0; i < this.answer.length; i++) {
					total = total - single
				}
				this.score = total;
				this.integral = num - this.answer.length;
				//更新用户积分，仅限第一次刷新
				if (storage.get('isLoad').length<=0) {
					storage.set('isLoad', true);
					this.updIntegral();
				}
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
			}
		},
		onLoad(option) {
			let result = JSON.parse(decodeURIComponent(option.answer));
			this.answer = result;
			this.count = this.answer.length;
			//一共有多少题，按照题目数量平均分配分数。
			this.calculateScore(5);
		},
		onBackPress() {
			this.redirectTo("/pages/exam/exam");
		}
	}
</script>

<style lang="scss">
	.question {
		width: 100%;
		height: 10em;
		background-color: $uni-color-success;
		line-height: 3.125em;
		color: #FFFFFF;
		font-size: 34rpx;
	}

	.wrong-answer {
		padding: 0.4375em;
	}
</style>
