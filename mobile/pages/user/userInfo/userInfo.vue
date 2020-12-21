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
		<u-modal v-model="showPhoneModal" title="更换手机号码" show-cancel-button="true" @confirm="getValue(user)" @cancel="delValue">
			<view class="wrap">
				<u-form>
					<u-form-item label="新手机号" :label-position="position">
						<u-input v-model="user2.userPhone" placeholder="请输入新手机号码"></u-input>
					</u-form-item>
				</u-form>
			</view>
		</u-modal>

		<!-- 邮箱模态框 -->
		<u-modal v-model="showEmailModal" title="更换电子邮箱" show-cancel-button="true" @confirm="getValue(user)" @cancel="delValue">
			<view class="wrap">
				<u-form>
					<u-form-item label="新邮箱地址" :label-position="position">
						<u-input v-model="user2.userEmail" placeholder="请输入新邮箱地址"></u-input>
					</u-form-item>
				</u-form>
			</view>
		</u-modal>
	</view>
</template>

<script>
	import storage from '@/common/storage.js'

	export default {
		data() {
			return {
				user: {},
				user2: {}, //用于存储用户输入的数据
				showPwdModal: false,
				showPhoneModal: false,
				showEmailModal: false,
				position: 'top',
				content: `<input/>`,
				pwd: {
					old_pwd: '',
					new_pwd: '',
					repeat: ''
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
			updUser() {
				this.$u.api.updUser(this.user).then(resp => {
					this.$refs.toast.show({
						title: resp.data.msg,
						type: resp.data.type,
						position: 'top'
					})
				})
			},
			getValue(value) {
				console.log(value);
			},
			delValue(type) {
				if (type == 'pwd') {
					this.pwd = {}
				} else {
					this.user2 = {}
				}
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
</style>
