<template>
	<view v-if="!isLogin" class="login">
		<view class="login-form">
			<view class="title">
				个人注册
			</view>
			<view>
				<u-row>
					<u-col span="12">
						<view style="margin-bottom: 15rpx;">用户名</view>
						<view :class="wrong.name.isWrong?'wrong':''">
							<input type="text" v-model="user.userName" placeholder="请输入用户名" @input="validateUsername" />
							<span>{{wrong.name.msg}}</span>
						</view>
					</u-col>
				</u-row>

				<u-row>
					<u-col span="12">
						<view style="margin-bottom: 15rpx;">密码</view>
						<view :class="wrong.pwd.isWrong?'wrong':''">
							<input type="password" v-model="user.userPwd" placeholder="请输入密码" @input="validatePwd" />
							<span>{{wrong.pwd.msg}}</span>
						</view>
					</u-col>
				</u-row>

				<u-row>
					<u-col span="12">
						<view style="margin-bottom: 15rpx;">手机号码</view>
						<view :class="wrong.phone.isWrong?'wrong':''">
							<input type="text" v-model="user.userPhone" placeholder="请输入手机号码" @input="validatePhone" />
							<span>{{wrong.phone.msg}}</span>
						</view>
					</u-col>
				</u-row>

				<u-row>
					<u-col span="12">
						<view style="margin-bottom: 15rpx;">电子邮箱</view>
						<view :class="wrong.email.isWrong?'wrong':''">
							<input type="text" v-model="user.userEmail" placeholder="请输入电子邮箱地址" @input="validateEmail" />
							<span>{{wrong.email.msg}}</span>
						</view>
					</u-col>
				</u-row>
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
				user: {
					userName: '',
					userPwd: ''
				},
				wrong: {
					name: {
						isWrong: false,
						msg: ''
					},
					pwd: {
						isWrong: false,
						msg: ''
					},
					phone: {
						isWrong: false,
						msg: ''
					},
					email: {
						isWrong: false,
						msg: ''
					}
				}
			}
		},
		methods: {
			register() {
				if (this.user.userName == '') {
					this.wrong.name.isWrong = true;
					this.wrong.name.msg = "请输入用户名";
					this.user.pass = false;
				}
				if (this.user.userPwd == '') {
					this.wrong.pwd.isWrong = true;
					this.wrong.pwd.msg = "请输入密码";
					this.user.pass = false;
				}
				if (this.user.userPhone == '') {
					this.user.userPhone=null;
				}
				if (this.user.userEmail == '') {
					this.user.userEmail=null;
				}
				//输入校验全部通过后进行请求
				if (this.user.pass) {
					this.$u.api.userRegister(this.user).then(resp => {
						let data = resp.data.obj;
						if (resp.data.code == 1) {
							this.$msg.sendSuccess(this, resp.data.msg);
							setTimeout(() => {
								this.$jump.switchTab("/pages/user/user")
							}, 1000)
						} else {
							//后台返回的重复性校验
							for (let i = 0; i < data.length; i++) {
								if (data[i] == '用户名已被注册') {
									this.wrong.name.isWrong = true;
									this.wrong.name.msg = data[i];
									this.user.pass = false;
								}
								if (data[i] == '手机号码已被使用') {
									this.wrong.phone.isWrong = true;
									this.wrong.phone.msg = data[i];
									this.user.pass = false;
								}
								if (data[i] == '电子邮箱已被使用') {
									this.wrong.email.isWrong = true;
									this.wrong.email.msg = data[i];
									this.user.pass = false;
								}
							}
						}
					})
				}
			},
			//用户名校验
			validateUsername() {
				let flag = false;
				let name = this.user.userName;
				let regx = new RegExp(/^\w{3,20}$/)
				if (name == '') {
					this.wrong.name.isWrong = true;
					this.wrong.name.msg = "请输入用户名";
				} else if (name.length < 3 || name.length > 13) {
					this.wrong.name.isWrong = true;
					this.wrong.name.msg = "用户名的长度为3至13位数之间";
				} else if (!regx.test(name)) {
					this.wrong.name.isWrong = true;
					this.wrong.name.msg = "用户名只能由数字、26个英文字母或者下划线组成";
				} else {
					flag = true
					this.wrong.name.isWrong = false;
					this.wrong.name.msg = "";
				}
				this.user.pass = flag;
			},
			//密码校验
			validatePwd() {
				let flag = false;
				let pwd = this.user.userPwd;
				let regx = new RegExp(/^[a-zA-Z]\w{5,17}$/);
				if (pwd == '') {
					this.wrong.pwd.isWrong = true;
					this.wrong.pwd.msg = "请输入密码";
				} else if (!regx.test(pwd)) {
					this.wrong.pwd.isWrong = true;
					this.wrong.pwd.msg = "密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线";
				} else {
					flag = true
					this.wrong.pwd.isWrong = false;
					this.wrong.pwd.msg = "";
				}
				this.user.pass = flag;
			},
			//手机号码校验
			validatePhone() {
				let flag = false;
				let phone = this.user.userPhone;
				let regx = new RegExp(/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/);
				if (!regx.test(phone) && phone != '') {
					this.wrong.phone.isWrong = true;
					this.wrong.phone.msg = "请输入有效的电话号码";
				} else {
					flag = true
					this.wrong.phone.isWrong = false;
					this.wrong.phone.msg = "";
				}
				this.user.pass = flag;
			},
			//邮箱校验
			validateEmail() {
				let flag = false;
				let email = this.user.userEmail;
				let regx = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)
				if (!regx.test(email) && email != '') {
					this.wrong.email.isWrong = true;
					this.wrong.email.msg = "请输入有效的电子邮箱地址";
				} else {
					flag = true
					this.wrong.email.isWrong = false;
					this.wrong.email.msg = "";
				}
				this.user.pass = flag;
			}
		}
	}
</script>

<style scoped lang="scss">
	.login-btn button {
		margin-bottom: 1em;
		border-radius: 15rpx;
		background-color: $u-type-primary;
		color: #FFFFFF;
	}

	.login {
		min-height: 100vh;
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

	input {
		min-height: 38px;
		font-size: 28rpx;
		color: #303133;
		flex: 1;
		border-radius: 6rpx;
		border: 1px solid #dcdfe6;
		padding: 0 11px;
	}

	.wrong {
		color: $u-type-error;
	}

	.wrong input {
		border: 1px solid $u-type-error;
	}
</style>
