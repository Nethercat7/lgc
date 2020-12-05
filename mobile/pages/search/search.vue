<template>
	<view>
		<u-row gutter="16">
			<u-col span="12">
				<view>
					<u-search placeholder="点击进行搜索" :show-action="true" :animation="true" @focus="goto('/pages/search/searchList/searchList')"></u-search>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<view class="border-left"><span class="u-padding-10">热门搜索</span></view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<u-tag class="tag" v-for="item in tags" :text="item" type="success" @click="getDetail(item)" />
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<view class="border-left"><span class="u-padding-10">Tips:</span></view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<view class="u-text-center">{{tips}}</view>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
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
				tips: '你属于:傻不拉几'
			};
		},
		methods: {
			goto(url) {
				uni.navigateTo({
					url: url
				});
			},
			getDetail(value) {
				console.log(value)
			}
		}
	}
</script>

<style lang="scss">
	.tag{
		margin: 0.4375em;
		display: inline-flex;
	}
</style>
