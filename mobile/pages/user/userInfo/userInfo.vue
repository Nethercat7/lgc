<template>
	<view class="setting">
		<u-cell-item icon="account" title="用户名" :value="user.userName"></u-cell-item>
		<u-cell-item icon="lock-open" title="密码" @click="showPwdModal=true"></u-cell-item>
		<u-cell-item icon="phone" title="手机" :value="user.userPhone" @click="showPhoneModal=true"></u-cell-item>
		<u-cell-item icon="email" title="电子邮箱" :value="user.userEmail" @click="showEmailModal=true"></u-cell-item>

		<!-- 密码模态框 -->
		<u-modal v-model="showPwdModal" title="修改密码" show-cancel-button="true" @confirm="updUser('pwd')" @cancel="delValue('pwd')">
			<view class="wrap">
				<u-row>
					<u-col span="12">
						<view :class="wrong.oldPwdWrong?'wrong':''">
							<input type="password" v-model="pwd.oldPwd" placeholder="请输入旧密码" @input="validateOldPwd" />
							<span>{{wrong.oldPwd}}</span>
						</view>
					</u-col>
				</u-row>
				<u-row>
					<u-col span="12">
						<view :class="wrong.isWrong?'wrong':''">
							<input type="password" v-model="pwd.newPwd" placeholder="请输入新密码" @input="validatePwd" />
							<span>{{wrong.msg}}</span>
						</view>
					</u-col>
				</u-row>
				<u-row>
					<u-col span="12">
						<view :class="wrong.repeatWrong?'wrong':''">
							<input type="password" v-model="pwd.repeat" placeholder="请重复新密码" @input="validateRepeate" />
							<span>{{wrong.repeat}}</span>
						</view>
					</u-col>
				</u-row>
			</view>
		</u-modal>

		<!-- 手机模态框 -->
		<u-modal v-model="showPhoneModal" title="更换手机号码" show-cancel-button="true" @confirm="updUser('phone')" @cancel="delValue">
			<view class="wrap">
				<u-row>
					<u-col span="12">
						<view :class="wrong.isWrong?'wrong':''">
							<input type="text" v-model="data" placeholder="请输入新手机号" @input="validatePhone" />
							<span>{{wrong.msg}}</span>
						</view>
					</u-col>
				</u-row>
			</view>
		</u-modal>

		<!-- 邮箱模态框 -->
		<u-modal v-model="showEmailModal" title="更换电子邮箱" show-cancel-button="true" @confirm="updUser('email')" @cancel="delValue">
			<view class="wrap">
				<u-row>
					<u-col span="12">
						<view :class="wrong.isWrong?'wrong':''">
							<input type="text" v-model="data" placeholder="请输入新邮箱地址" @input="validateEmail" />
							<span>{{wrong.msg}}</span>
						</view>
					</u-col>
				</u-row>
			</view>
		</u-modal>

		<u-toast ref="toast"></u-toast>
	</view>
</template>

<script>
	import storage from '@/common/storage.js'

	export default {
		data() {
			return {
				user: {},
				showPwdModal: false,
				showPhoneModal: false,
				showEmailModal: false,
				position: 'top',
				content: `<input/>`,
				pwd: {
					oldPwd: '',
					newPwd: '',
					repeat: ''
				},
				data: null,
				wrong: {
					isWrong: false,
					repeatWrong: false,
					oldPwdWrong: false,
				}
			};
		},
		methods: {
			getUser() {
				this.$u.api.getUser({
					name: storage.getUser('token').username
				}).then(resp => {
					this.user = resp.data.obj;
				})
			},
			updUser(type) {
				//保持模态框为打开状态，成功之后再关闭
				if (type == 'phone') {
					this.showPhoneModal = true;
				} else if (type == 'email') {
					this.showEmailModal = true;
				} else {
					this.showPwdModal = true;
				}

				if (this.wrong.pass) {
					if (type == 'phone') {
						this.$u.api.updUserPhone({
							id: this.user.userId,
							phone: this.data
						}).then(res => {
							if (res.data.code == 1) {
								this.data = null;
								this.getUser();
								this.showPhoneModal = false;
							} else if (res.data.code == 3) {
								this.wrong.isWrong = true;
								this.wrong.msg = res.data.msg;
							}
							this.$msg.send(this, res.data.msg, res.data.type);
						})
					} else if (type == 'email') {
						this.$u.api.updUserEmail({
							id: this.user.userId,
							email: this.data
						}).then(res => {
							if (res.data.code == 1) {
								this.data = null;
								this.getUser();
								this.showEmailModal = false;
							} else if (res.data.code == 4) {
								this.wrong.isWrong = true;
								this.wrong.msg = res.data.msg;
							}
							this.$msg.send(this, res.data.msg, res.data.type);
						})
					} else {
						this.$u.api.updUserPwd({
							newPwd: this.pwd.newPwd,
							oldPwd: this.pwd.oldPwd,
							id: this.user.userId
						}).then(res => {
							if (res.data.code == 1) {
								this.pwd = {}
								this.getUser();
								this.showPwdModal = false;
								setTimeout(() => {
									storage.remove('token');
									this.$jump.switchTab('/pages/user/user');
								}, 1000)
							} else if (res.data.code == 5) {
								this.wrong.oldPwdWrong = true;
								this.wrong.oldPwd = res.data.msg;
							}
							this.$msg.send(this, res.data.msg, res.data.type);
						})
					}
				}
			},
			delValue(type) {
				if (type == 'pwd') {
					this.pwd = {}
					this.wrong.isWrong = false;
					this.wrong.repeatWrong = false;
					this.wrong.msg = "";
					this.wrong.repeat = "";
				} else {
					this.data = null
					this.wrong.isWrong = false;
					this.wrong.msg = "";
				}
			},
			//密码校验
			validatePwd() {
				let flag = false;
				let pwd = this.pwd.newPwd;
				let regx = new RegExp(/^[a-zA-Z]\w{5,17}$/);
				if (pwd == '') {
					this.wrong.isWrong = true;
					this.wrong.msg = "请输入密码";
				} else if (!regx.test(pwd)) {
					this.wrong.isWrong = true;
					this.wrong.msg = "密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线";
				} else {
					flag = true
					this.wrong.isWrong = false;
					this.wrong.msg = "";
				}

				if (pwd != this.pwd.repeat) {
					flag = false;
					this.wrong.repeatWrong = true;
					this.wrong.repeat = "输入的密码与新密码不一致";
				} else {
					flag = true;
					this.wrong.repeatWrong = false;
					this.wrong.repeat = "";
				}

				this.wrong.pass = flag;
			},
			//重复密码校验
			validateRepeate() {
				let flag = false;
				if (this.pwd.newPwd != this.pwd.repeat) {
					this.wrong.repeatWrong = true;
					this.wrong.repeat = "输入的密码与新密码不一致";
				} else {
					flag = true;
					this.wrong.repeatWrong = false;
					this.wrong.repeat = "";
				}
				this.wrong.pass = flag;
			},
			validateOldPwd() {
				this.wrong.oldPwdWrong = false;
				this.wrong.oldPwd = "";
			},
			//手机号码校验
			validatePhone() {
				let flag = false;
				let phone = this.data;
				let regx = new RegExp(/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/);
				if (!regx.test(phone) && phone != '') {
					this.wrong.isWrong = true;
					this.wrong.msg = "请输入有效的电话号码";
				} else {
					flag = true
					this.wrong.isWrong = false;
					this.wrong.msg = '';
				}
				this.wrong.pass = flag;
			},
			//邮箱校验
			validateEmail() {
				let flag = false;
				let email = this.data;
				let regx = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/)
				if (!regx.test(email) && email != '') {
					this.wrong.isWrong = true;
					this.wrong.msg = "请输入有效的电子邮箱地址";
				} else {
					flag = true
					this.wrong.isWrong = false;
					this.wrong.msg = "";
				}
				this.wrong.pass = flag;
			}
		},
		onLoad() {
			this.getUser()
		}
	}
</script>

<style lang="scss">
	.setting u-cell-item {
		display: block;
		background-color: #FFFFFF;
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
