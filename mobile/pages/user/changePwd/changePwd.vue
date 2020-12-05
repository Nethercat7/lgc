<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<u-form :model="pwd">
					<u-form-item label="旧密码" :label-position="position" required>
						<u-input v-model="pwd.oldPwd" type="password" />
					</u-form-item>
					<u-form-item label="新密码" :label-position="position" required>
						<u-input v-model="pwd.newPwd" type="password" />
					</u-form-item>
					<u-form-item label="重复密码" :label-position="position" required>
						<u-input v-model="pwd.repeatPwd" type="password" />
					</u-form-item>
				</u-form>
				<u-button @click="submit">提交</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pwd: {},
				position: 'top'
			};
		},
		methods: {
			submit() {
				console.log(this.pwd)
			}
		}
	}
</script>

<style lang="scss">

</style>
