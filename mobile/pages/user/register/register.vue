<template>
	<view v-if="!isLogin" class="login">
		<view class="login-form">
			<view class="title">
				个人注册
			</view>
			<view>
				<u-form class="form">
					<u-form-item left-icon="info-circle" :left-icon-style="white" :border-bottom="false">
						<u-input v-model="user.userName" placeholder="请输入用户名" placeholder-style="color:white" />
					</u-form-item>
					<u-form-item left-icon="lock-open" :left-icon-style="white" :border-bottom="false">
						<u-input v-model="user.userPwd" type="password" placeholder="请输入密码" placeholder-style="color:white" />
					</u-form-item>
					<u-form-item left-icon="phone" :left-icon-style="white" :border-bottom="false">
						<u-input v-model="user.userPhone"  placeholder="请输入手机号码" placeholder-style="color:white" />
					</u-form-item>
					<u-form-item left-icon="email" :left-icon-style="white" :border-bottom="false">
						<u-input v-model="user.userEmail"  placeholder="请输入电子邮箱" placeholder-style="color:white" />
					</u-form-item>
				</u-form>
			</view>
			<view class="login-btn" style="padding-top: 3em;">
				<button @click="register">注册</button>
			</view>
		</view>
		<u-toast ref="toast" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {}
			};
		},
		methods: {
			register() {
				this.$u.api.userRegister(this.user).then(resp => {
					if (resp.data.code == 1) {
						setTimeout(()=>{
							this.$jump.switchTab("/pages/user/user")
						},1000)
					}
					this.$refs.toast.show({
						title: resp.data.msg,
						type: resp.data.type,
						position: 'top'
					})
				})
			}
		}
	}
</script>

<style lang="scss">
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

	.title {
		padding-top: 70rpx;
		padding-bottom: 70rpx;
		text-align: center;
		font-size: 70rpx;
	}

	.form u-form-item {
		display: block;
		border-bottom: 1px solid white;
	}

	.u-icon text,
	input {
		color: white !important;
	}
</style>
