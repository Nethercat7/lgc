<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<view class="u-text-center question">
					{{data[total].item}}属于什么垃圾
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" class="answer-btn">
			<u-col span="12">
				<u-button :custom-style="green" type="primary" size="default" @click="getNext('厨余垃圾')">厨余垃圾</u-button>
			</u-col>
			<u-col span="12">
				<u-button :custom-style="blue" type="primary" size="default" @click="getNext('可回收垃圾')">可回收垃圾</u-button>
			</u-col>
			<u-col span="12">
				<u-button :custom-style="red" type="primary" size="default" @click="getNext('有害垃圾')">有害垃圾</u-button>
			</u-col>
			<u-col span="12">
				<u-button :custom-style="gray" type="primary" size="default" @click="getNext('其他垃圾')">其他垃圾</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: [{
						item: "用过的尿不湿",
						answer: "有害垃圾"
					},
					{
						item: "手机壳",
						answer: "可回收垃圾"
					},
					{
						item: "苹果核",
						answer: "厨余垃圾"
					},
					{
						item: "餐巾纸",
						answer: "其他垃圾"
					}
				],
				count: 0,
				total: 0,
				wrong: [],
				//button按钮样式
				green: {
					color: '#fff',
					backgroundColor: 'rgb(41, 152, 86) !important'
				},
				blue: {
					color: '#fff',
					backgroundColor: 'rgb(71, 154, 214) !important'
				},
				red: {
					color: '#fff',
					backgroundColor: 'rgb(220, 108, 125) !important'
				},
				gray: {
					color: '#fff',
					backgroundColor: 'rgb(145, 152, 158) !important'
				}
			};
		},
		methods: {
			getNext(value) {
				if(this.count<this.data.length){
					if(value!=this.data[this.count].answer){
						this.data[this.count].errAnswer=value
						this.wrong.push(this.data[this.count])
					}
					this.count++;
					if(this.total<this.data.length-1){
						this.total++;
					}else{
						this.goto('/pages/exam/submitResult/submitResult?answer='+encodeURIComponent(JSON.stringify(this.wrong)))
					}
				}
			},
			goto(url) {
				uni.redirectTo({
					url: url
				})
			}
		},
		onLoad() {
			
		}
	}
</script>

<style lang="scss">
	.question {
		width: 100%;
		height: 10em;
		background-color: $uni-color-success;
		line-height: 10em;
		color: #FFFFFF;
		font-size: 35rpx;
	}

	.answer-btn .u-col {
		padding: 30rpx !important;
	}
</style>
