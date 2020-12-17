<template>
	<view class="wrap">
		<u-toast ref="toast" />
		<view>
			<u-form label-position="top">
				<u-form-item label="用户名">
					<u-input v-model="user.userName" />
				</u-form-item>
				<u-form-item label="密码">
					<u-input v-model="user.userPwd" type="password" />
				</u-form-item>
			</u-form>
		</view>
		<view>
			<u-button type="pirmary" @click="register">注册</u-button>
		</view>
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
						this.$jump.redirect("/pages/user/login/login")
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

</style>
