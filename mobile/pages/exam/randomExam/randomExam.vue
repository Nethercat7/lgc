<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<view class="u-text-center question">
					{{data[total].garbageName}}属于什么垃圾？
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16" class="answer-btn" v-for='item in categories'>
			<u-col span="12">
				<u-button type="primary" size="default" @click="getNext(item.gcName)">{{item.gcName}}</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: [
					{garbageName:'temp'}
				],
				count: 0,
				total: 0,
				wrong: [],
				categories:[],
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
					if(value!=this.data[this.count].gcName){
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
			},
			getGarbages(){
				this.$u.api.getGarbages({
					num:5
				}).then(resp=>{
					this.data=resp.data.obj;
				})
			},
			getCategories(){
				this.$u.api.getGarbageCategories().then(resp=>{
					this.categories=resp.data.obj
				})
			}		
		},
		onLoad() {
			this.getGarbages();
			this.getCategories();
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
		padding: 10rpx !important;
	}
</style>
