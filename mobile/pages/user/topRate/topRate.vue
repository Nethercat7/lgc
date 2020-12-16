<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<view class="border-left">
					<span class="u-padding-10">积分排行榜</span>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<u-table>
					<u-tr class="u-tr" v-for="(item,index) in users" :key="item.index">
						<u-td class="u-td">
							{{index+1}}
						</u-td>
						<u-td class="u-td">
							{{item.userName}}
						</u-td>
						<u-td class="u-td">
							{{item.userIntegral}}
						</u-td>
					</u-tr>
					<view class="border-left">
						<span class="u-padding-10">宁的排名</span>
					</view>
					<u-tr class="u-tr">
						<u-td class="u-td">
							{{user.rate}}
						</u-td>
						<u-td class="u-td">
							{{user.name}}
						</u-td>
						<u-td class="u-td">
							{{user.integral}}
						</u-td>
					</u-tr>
				</u-table>
			</u-col>
		</u-row>
	</view>
</template>

<script>
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
			getUserRate(id){
				this.$u.api.getUserRate({
					id:id
				}).then(resp=>{
					this.user=resp.data.obj;
				})
			}
		},
		onLoad(option) {
			this.getTop10Users();
			this.getUserRate(option.id);
		}
	}
</script>

<style lang="scss">
	.u-table,
	.u-td {
		border: 0 !important
	}
</style>
