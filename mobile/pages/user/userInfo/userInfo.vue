<template>
	<view class="setting">
		<u-cell-item icon="account" title="用户名" :value="user.userName"></u-cell-item>
		<u-cell-item icon="lock-open" title="密码" @click="showPwdModal=true"></u-cell-item>
		<u-cell-item icon="phone" title="手机" :value="user.userPhone" @click="showPhoneModal=true"></u-cell-item>
		<u-cell-item icon="email" title="电子邮箱" :value="user.userEmail" @click="showEmailModal=true"></u-cell-item>

		<!-- 密码模态框 -->
		<u-modal v-model="showPwdModal" title="修改密码" show-cancel-button="true" @confirm="getValue(pwd)" @cancel="delValue('pwd')">
			<view class="wrap">
				<u-form>
					<u-form-item label="旧密码" :label-position="position">
						<u-input v-model="pwd.old_pwd" type="password" placeholder="请输入旧密码"></u-input>
					</u-form-item>
					<u-form-item label="新密码" :label-position="position">
						<u-input v-model="pwd.new_pwd" type="password" placeholder="请输入新密码"></u-input>
					</u-form-item>
					<u-form-item label="重复密码" :label-position="position">
						<u-input v-model="pwd.repeat" type="password" placeholder="请输重复刚才输入的新密码"></u-input>
					</u-form-item>
				</u-form>
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
					old_pwd: '',
					new_pwd: '',
					repeat: ''
				},
				data: null,
				wrong: {
					isWrong: false
				}
			};
		},
		methods: {
			submit() {
				this.updUser();
			},
			confirm(obj) {
				this.gender = obj[0].label
				this.user.userGender = obj[0].value
			},
			getUser() {
				this.$u.api.getUser({
					name: storage.getUser('token').username
				}).then(resp => {
					this.user = resp.data.obj;
				})
			},
			updUser(type) {
				if (this.wrong.pass) {
					if (type == 'phone') {
						//保持模态框为打开状态，成功之后再关闭
						this.showPhoneModal = true;
						this.$u.api.updUserPhone({
							id: this.user.userId,
							phone: this.data
						}).then(res => {
							if (res.data.code == 1) {
								this.data = null;
								this.getUser();
								this.showPhoneModal = false;
							} else {
								this.wrong.isWrong = true;
								this.wrong.msg = res.data.msg;
							}
							this.$msg.send(this, res.data.msg, res.data.type);
						})
					} else if (type == 'email') {
						this.showEmailModal = true;
						this.$u.api.updUserEmail({
							id: this.user.userId,
							email: this.data
						}).then(res => {
							if (res.data.code == 1) {
								this.data = null;
								this.getUser();
								this.showEmailModal = false;
							} else {
								this.wrong.isWrong = true;
								this.wrong.msg = res.data.msg;
							}
							this.$msg.send(this, res.data.msg, res.data.type);
						})
					}
				}
			},
			delValue(type) {
				if (type == 'pwd') {
					this.pwd = {}
				} else {
					this.data = null
					this.wrong.isWrong = false;
					this.wrong.msg = "";
				}
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
