<template>
	<view class="wrap">
		<u-toast ref="toast" />
		<view>
			<u-form label-position="top">
				<u-form-item label="用户名">
					<u-input v-model="name" />
				</u-form-item>
				<u-form-item label="密码">
					<u-input v-model="pwd" type="password" />
				</u-form-item>
			</u-form>
		</view>
		<view class="btn">
			<u-button type="primary" @click="login">登录</u-button>
			<u-button type="primary" @click="$jump.navigate('/pages/user/register/register')">注册</u-button>
		</view>
	</view>
</template>

<script>
	import storage from '@/common/storage.js';

	export default {
		data() {
			return {
				name: '',
				pwd: ''
			};
		},
		methods: {
			login() {
				this.$u.post('/user/login?name=' + this.name + '&pwd=' + this.pwd).then(resp => {
					if (resp.data.code === 1) {
						storage.set('token', resp.data.obj);
						this.$jump.switchTab('/pages/user/user?isLogin=' + true);
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
	.btn button {
		margin-bottom: 1em;
	}
</style>
