<template>
	<view class="wrap">
		<button class="shadow opt-btn" type="primary" @click="navigateTo('/pages/exam/randomExam/randomExam')">开始随机小测验</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				optionCard:{
					height:'6.25em'
				}
			};
		},
		methods:{
			navigateTo(url){
				uni.navigateTo({
					url:url
				})
			}
		}
	}
</script>

<style lang="scss">
	.opt-btn{
		height:5em;
		line-height: 5em;
	}
</style>
