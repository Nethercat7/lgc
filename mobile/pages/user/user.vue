<template>
	<view>
		<view v-if="isLogin">
			<view class="u-text-center profile">
				<u-avatar :src="user.userAvatar" size="150"></u-avatar>
				<view class="u-font-xl">
					{{user.userName}}
				</view>
			</view>

			<view class="u-font-xl integral shadow">
				<span class="float-left">我的积分</span>
				<span class="float-right" style="font-size: 55rpx;">{{user.userIntegral}}</span>
			</view>

			<u-row class="card">
				<u-col span="4">
					<view class="opt-card" @click="$jump.navigate('/pages/exam/randomExam/randomExam')">
						<u-icon name="edit-pen" size="30rpx" style="padding-right: 10rpx;"></u-icon>问答挑战
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="$jump.navigate('/pages/user/topRate/topRate?id='+user.userId)">
						<u-icon name="pushpin" size="30rpx" style="padding-right: 10rpx;"></u-icon>积分排行榜
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="$jump.navigate('/pages/user/userInfo/userInfo')">
						<u-icon name="account" size="30rpx" style="padding-right: 10rpx;"></u-icon>个人资料
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="exit">
						<u-icon name="integral-fill" size="30rpx" style="padding-right: 10rpx;"></u-icon>退出登录
					</view>
				</u-col>
			</u-row>

			<!-- <u-row>
				<u-col span="6">
					<u-cell-group>
						<u-cell-item icon="list" title="问答挑战" @click="$jump.navigate('/pages/exam/randomExam/randomExam')"></u-cell-item>
						<u-cell-item icon="list" title="排行榜" @click="$jump.navigate('/pages/user/topRate/topRate?id='+user.userId)"></u-cell-item>
						<u-cell-item icon="file-text-fill" title="个人资料" @click="$jump.navigate('/pages/user/userInfo/userInfo')"></u-cell-item>
						<u-cell-item icon="setting-fill" title="系统设置"></u-cell-item>
						<u-cell-item icon="integral-fill" title="退出登入" @click="exit"></u-cell-item>
					</u-cell-group>
				</u-col>
			</u-row> -->

		</view>
		<view v-if="!isLogin">
			<!-- 微信登录 -->
			<u-row gutter="16">
				<u-col span="12">
					<u-button open-type="getUserInfo" @click="getInfo">微信登录</u-button>
				</u-col>
			</u-row>

			<!-- 用户名登录 -->
			<u-row gutter="16">
				<u-col span="12">
					<u-button @click="$jump.navigate('/pages/user/login/login')">用户名登录</u-button>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	import storage from '@/common/storage.js';

	export default {
		data() {
			return {
				user: {},
				isLogin: false,
			}
		},
		methods: {
			exit() {
				storage.remove('token');
				this.isLogin = false;
			},
			getUser() {
				this.$u.api.getUser({
					name: storage.getUser('token').username
				}).then(resp => {
					this.user = resp.data.obj;
				})
			},
			getInfo() {
				uni.login({
					success(resp) {
						uni.getUserInfo({
							success(resp) {
								console.log(resp);
							}
						})
					}
				})
			}
		},
		onShow() {
			//从导航栏切换过来时刷新用户数据
			if (storage.get('token').length > 0) {
				this.getUser();
				this.isLogin = true;
			} else {
				this.isLogin = false;
			}
		}
	}
</script>

<style lang="scss">
	.profile {
		color: #FFFFFF;
		background-color: #19be6b;
		padding-top: 2em;
		height: 400rpx;
	}

	.profile view {
		padding: 10rpx;
	}

	.integral {
		position: relative;
		height: 150rpx;
		margin-left: 35rpx;
		margin-right: 35rpx;
		border-radius: 13rpx;
		bottom: 70rpx;
		background-color: rgba(0, 0, 0, 0.3);
		color: white;
		line-height: 150rpx;
		//background-image: url('https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3491640376,2236282874&fm=26&gp=0.jpg');
		background-position: center center;
	}

	.card {
		position: relative;
		bottom: 60rpx;
	}

	.opt-card {
		width: 100%;
		height: 130rpx;
		line-height: 130rpx;
		text-align: center;
		background-color: #f9f9f9;
		border-radius: 15rpx;
		margin-bottom: 30rpx;
	}

	.opt-card:active {
		background-color: rgba($color: #f9f9f9, $alpha: 0.5);
	}
</style>
