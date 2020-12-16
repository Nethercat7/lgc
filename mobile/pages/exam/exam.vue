<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<u-button :custom-style="optionCard" type="primary" @click="navigateTo('/pages/exam/randomExam/randomExam')">开始随机小测试</u-button>
			</u-col>
		</u-row>
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

</style>
