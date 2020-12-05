<template>
	<view class="wrap">
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
						获得77点积分
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
						{{item.item}}属于：{{item.answer}}
					</view>
					<view>
						宁的答案为：{{item.errAnswer}}
					</view>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<u-button @click="redirectTo('/pages/exam/randomExam/randomExam')" v-if="count<5">再答一次</u-button>
				<u-button @click="redirectTo('/pages/index/index')" v-if="count>=5">返回首页</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				score: 0,
				answer: [],
				count:4
			}
		},
		methods: {
			redirectTo(url){
				uni.redirectTo({
					url:url
				})
			},
			calculateScore(num) {
				let total = 100;
				let single = 100 / num
				for (let i = 0; i < this.answer.length; i++) {
					total = total - single
				}
				this.score = total
			}
		},
		onLoad(option) {
			let result = JSON.parse(decodeURIComponent(option.answer));
			this.answer = result;
			this.calculateScore(4);
		},
		onBackPress() {
			this.redirectTo("/pages/exam/exam")
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
