<template>
	<view class="wrap">
		<!-- 积分排行前十 -->
		<view>
			<u-row>
				<u-col span="12">
					<view class="border-left">
						<span class="u-padding-10">积分排行榜前10</span>
					</view>
				</u-col>
			</u-row>
			<u-row class="u-border-bottom user" v-for="item,index in users" :key="item.userId">
				<u-col span="2">
					<view class="rate index" style="padding-left:15rpx;">
						{{index+1}}
					</view>
				</u-col>
				<u-col span="3">
					<view class="avatar">
						<u-avatar :src="item.userAvatar" size="70"></u-avatar>
					</view>
				</u-col>
				<u-col span="4">
					<view class="rate">
						{{item.userNickname}}
					</view>
				</u-col>
				<u-col span="3">
					<view class="rate">
						<u-icon name="integral"></u-icon>
						<span style="padding-left: 10rpx;">{{item.userIntegral}}</span>
					</view>
				</u-col>
			</u-row>
			<!-- 用户积分排名 -->
			<u-row>
				<u-col span="12">
					<view class="border-left">
						<span class="u-padding-10">您的排名</span>
					</view>
				</u-col>
				<u-col span="12">
					<u-row class="user">
						<u-col span="2">
							<view class="rate index" style="padding-left:15rpx;">
								{{user.rate}}
							</view>
						</u-col>
						<u-col span="3">
							<view class="avatar">
								<u-avatar :src="user.avatar" size="70"></u-avatar>
							</view>
						</u-col>
						<u-col span="4">
							<view class="rate u-line-1">
								{{user.nickname}}
							</view>
						</u-col>
						<u-col span="3">
							<view class="rate">
								<u-icon name="integral"></u-icon>
								<span style="padding-left: 10rpx;">{{user.integral}}</span>
							</view>
						</u-col>
					</u-row>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	import storage from '@/common/storage.js';
	
	export default {
		data() {
			return {
				users: [],
				user: {}
			}
		},
		methods: {
			getTop10Users() {
				this.$u.api.getUsers({
					rate: true
				}).then(resp => {
					this.users = resp.data.obj;
				})
			},
			getUserRate() {
				this.$u.api.getUserRate({
					id: storage.getUser().userId
				}).then(resp => {
					this.user = resp.data.obj;
					console.log(this.user);
				})
			}
		},
		onLoad(option) {
			this.getTop10Users();
			this.getUserRate();
		}
	}
</script>

<style scoped lang="scss">
	.user {
		display: block;
		height: 100rpx;
	}

	.rate {
		line-height: 100rpx;
	}

	.avatar {
		position: relative;
		top: 15rpx;
	}

	.index {
		font-size: 30rpx;
		font-weight: 600;
	}

	.border-left {
		margin-bottom: 0;
	}
</style>
