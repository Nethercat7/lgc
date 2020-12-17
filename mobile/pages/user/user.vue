<template>
	<view class="wrap">
		<view v-if="isLogin">
			<u-row gutter="16">
				<u-col span="12">
					<view class="u-text-center profile">
						<u-avatar :src="user.userAvatar" size="150"></u-avatar>
						<view class="u-font-xl">
							{{user.userName}}
						</view>
						<view class="u-font-xl title">

						</view>
						<view class="u-font-xl">
							积分：{{user.userIntegral}}
						</view>
					</view>
				</u-col>
			</u-row>
			<u-row gutter="16">
				<u-col span="12">
					<u-cell-group>
						<u-cell-item icon="list" title="排行榜" @click="$jump.navigate('/pages/user/topRate/topRate?id='+user.userId)"></u-cell-item>
						<u-cell-item icon="file-text-fill" title="个人资料" @click="$jump.navigate('/pages/user/userInfo/userInfo')"></u-cell-item>
						<u-cell-item icon="setting-fill" title="系统设置"></u-cell-item>
						<u-cell-item icon="integral-fill" title="退出登入" @click="exit"></u-cell-item>
					</u-cell-group>
				</u-col>
			</u-row>
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
	.profile view {
		padding: 10rpx
	}

	.title {
		color: $uni-color-success
	}
</style>