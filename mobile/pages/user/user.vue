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
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="star" size="30rpx" style="padding-right: 10rpx;"></u-icon>我的收藏
					</view>
				</u-col>
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
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="eye-off" size="30rpx" style="padding-right: 10rpx;"></u-icon>隐私设置
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="setting" size="30rpx" style="padding-right: 10rpx;"></u-icon>系统设置
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="shopping-cart" size="30rpx" style="padding-right: 10rpx;"></u-icon>积分商城
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="arrow-upward" size="30rpx" style="padding-right: 10rpx;"></u-icon>上传分类数据
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="showModeal=true">
						<u-icon name="kefu-ermai" size="30rpx" style="padding-right: 10rpx;"></u-icon>意见反馈
					</view>
				</u-col>
				<u-col span="4">
					<view class="opt-card" @click="exit">
						<u-icon name="close" size="30rpx" style="padding-right: 10rpx;"></u-icon>退出登录
					</view>
				</u-col>
			</u-row>
			<u-modal v-model="showModeal" content="此选项暂未开放"></u-modal>

		</view>
		<view v-if="!isLogin" class="login">
			<view class="login-form">
				<view class="logo">
					<u-image style="display: inline-block;" width="200" height="200" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608492122005&di=d9797f03974a2acd2e684e16e2424e1b&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F86%2F10%2F01300000184180121920108394217.jpg"
					 shape="circle"></u-image>
				</view>
				<view>
					<u-form class="form">
						<u-form-item left-icon="info-circle" :left-icon-style="white" :border-bottom="false">
							<u-input v-model="name" placeholder="请输入用户名" placeholder-style="color:white" />
						</u-form-item>
						<u-form-item left-icon="lock-open" :left-icon-style="white" :border-bottom="false">
							<u-input v-model="pwd" type="password" placeholder="请输入密码" placeholder-style="color:white" />
						</u-form-item>
					</u-form>
				</view>
				<view class="login-btn" style="padding-top: 3em;">
					<button @click="login">登录</button>
					<button @click="$jump.navigate('/pages/user/register/register')">注册</button>
				</view>
			</view>
			<u-toast ref="toast" />
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
				pwd: '',
				white: {
					"color": "white"
				},
				showModeal: false
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
			login() {
				this.$u.post('/user/login?name=' + this.name + '&pwd=' + this.pwd).then(resp => {
					if (resp.data.code === 1) {
						storage.set('token', resp.data.obj);
						this.isLogin = true;
						this.getUser();
					}
					this.$refs.toast.show({
						title: resp.data.msg,
						type: resp.data.type,
						position: 'top'
					})
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
		background-color: $u-type-primary;
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

	.login-btn button {
		margin-bottom: 1em;
		border-radius: 15rpx;
		color: $u-type-success;
	}

	.login {
		background-color: $u-type-success;
		min-height: 100vh;
		color: #FFFFFF;
	}

	.login-form {
		width: 70%;
		margin: auto;
	}

	.logo {
		padding-top: 70rpx;
		text-align: center;
	}

	.form u-form-item {
		display: block;
		border-bottom: 1px solid white;
	}

	.form .u-icon text,
	input {
		color: white !important;
	}
</style>
