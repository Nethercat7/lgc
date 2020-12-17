<template>
	<view class="wrap">
		<u-toast ref="toast" />
		<u-row gutter="16">
			<u-col span="12">
				<u-form :model="user" ref="uForm">
					<u-form-item label="用户名" :label-position="position" disabled>
						<u-input v-model="user.userName" :disabled='true' />
					</u-form-item>
					<u-form-item label="手机号码" :label-position="position">
						<u-input v-model="user.userPhone" />
					</u-form-item>
					<u-form-item label="电子邮箱" :label-position="position">
						<u-input v-model="user.userEmail" type="email" />
					</u-form-item>
				</u-form>
				<u-button @click="submit">提交</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	import storage from '@/common/storage.js'

	export default {
		data() {
			return {
				user: {},
				show: false,
				position: 'top',
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
			}
		},
		onLoad() {
			this.getUser()
		}
	}
</script>

<style lang="scss">

</style>
