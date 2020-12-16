<template>
	<view class="wrap">
		<u-toast ref="toast" />
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
						<u-cell-item icon="list" title="排行榜" @click="goto('/pages/user/topRate/topRate')"></u-cell-item>
						<u-cell-item icon="file-text-fill" title="个人资料" @click="goto('/pages/user/userInfo/userInfo')"></u-cell-item>
						<u-cell-item icon="lock-fill" title="修改密码" @click="goto('/pages/user/changePwd/changePwd')"></u-cell-item>
						<u-cell-item icon="setting-fill" title="系统设置"></u-cell-item>
						<u-cell-item icon="integral-fill" title="退出登入" @click="exit"></u-cell-item>
					</u-cell-group>
				</u-col>
			</u-row>
		</view>
		<view v-if="!isLogin">
			<u-row gutter="16">
				<u-col span="12">
					<u-form label-position="top">
						<u-form-item label="用户名">
							<u-input v-model="name" />
						</u-form-item>
						<u-form-item label="密码">
							<u-input v-model="pwd" type="password" />
						</u-form-item>
					</u-form>
				</u-col>
			</u-row>
			<u-col span="12">
				<u-button type="primary" @click="login">登入</u-button>
			</u-col>
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
				name: '',
				pwd: ''
			};
		},
		methods: {
			goto(url, type) {
				if (type == 'redirect') {
					uni.redirectTo({
						url: url
					})
				} else {
					uni.navigateTo({
						url: url
					})
				}
			},
			login() {
				this.$u.post('/user/login?name=' + this.name + '&pwd=' + this.pwd).then(resp => {
					if (resp.data.code === 1) {
						storage.set('token', resp.data.obj);
						this.getUser();
						this.isLogin = true;
					}
					this.$refs.toast.show({
						title: resp.data.msg,
						type: resp.data.type,
						position: 'top'
					})
				})
			},
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
