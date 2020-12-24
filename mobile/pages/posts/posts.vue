<template>
	<view class="wrap">
		<view>
			<view class="posts-title">{{posts.title}}</view>
			<view class="posts-info">
				<span>
					<u-icon name="calendar"></u-icon>{{posts.postsAddTime}}
				</span>
				<span>
					<u-icon name="account-fill"></u-icon>{{posts.userNickname}}
				</span>
			</view>
		</view>
		<view class="posts-content">
			<u-parse :html="posts.postsContent"></u-parse>
		</view>
		<view class="posts-tags">
			<u-row>
				<u-col span="1">
					<u-icon name="tags"></u-icon>
				</u-col>
				<u-col span="11">
					<span v-for="item in tags">
						<u-tag :text="item" shape="circleLeft"></u-tag>
					</span>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	export default {

		data() {
			return {
				posts: {},
				tags: ["标签1", "标签2", "标签3"],
			}
		},
		methods: {

		},
		onLoad(option) {
			this.posts = JSON.parse(decodeURIComponent(option.obj));
		}
	}
</script>

<style scoped lang="scss">
	.posts-title {
		text-align: center;
		font-size: 50rpx;
	}

	.posts-info,
	.posts-tags {
		color: #777;
		margin-top: 30rpx;
		text-align: center;
	}

	.posts-info>span {
		padding: 30rpx;
	}

	.posts-content {
		margin-top: 70rpx;
	}



	.posts-tags u-icon {
		height: 50rpx;
		line-height: 50px;
	}

	.posts-tags span {
		display: inline-block;
		margin-right: 15rpx;
		margin-bottom: 15rpx;
	}

	.posts-tags /deep/ .u-tag {
		height: 50rpx;
	}
</style>
