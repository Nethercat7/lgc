<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<u-form :model="user" ref="uForm">
					<u-form-item label="头像" :label-position="position">
						<u-upload :action="action" :file-list="fileList" max-count="1"></u-upload>
					</u-form-item>
					<u-form-item label="姓名" :label-position="position" required>
						<u-input v-model="user.name" />
					</u-form-item>
					<u-form-item label="性别" :label-position="position" required>
						<u-input v-model="gender" type="select" @click="show=true" />
						<u-select v-model="show" :list="list" @confirm="confirm"></u-select>
					</u-form-item>
					<u-form-item label="手机号码" :label-position="position" required>
						<u-input v-model="user.phone" />
					</u-form-item>
					<u-form-item label="电子邮箱" :label-position="position">
						<u-input v-model="user.email" type="email" />
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
				user: {
					name: 'Nethercat7',
					gender: '1',
					phone: '18277365270',
					email: '1209322690@qq.com'
				},
				show: false,
				list: [{
						value: 1,
						label: '男'
					},
					{
						value: 0,
						label: '女'
					}
				],
				gender: '男',
				position: 'top',
				action: 'http://www.example.com/upload',
				fileList: [{
					url: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605900949663&di=3d1d282f8783f6eac45afc4f7511ed6c&imgtype=0&src=http%3A%2F%2Fpic4.zhimg.com%2F80%2Fv2-9a6828c2682d4cc28e9a24d49e87d99b_hd.jpg',
				}]
			};
		},
		methods: {
			submit() {
				console.log(this.user);
			},
			confirm(obj) {
				this.gender = obj[0].label
				this.user.gender = obj[0].value
			}
		}
	}
</script>

<style lang="scss">

</style>
