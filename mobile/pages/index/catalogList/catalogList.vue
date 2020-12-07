<template>
	<view>
		<u-row gutter="16">
			<u-col span="12">
				<u-table>
					<u-tr>
						<u-th>名称</u-th>
						<u-th>类别</u-th>
					</u-tr>
					<u-tr class="u-tr" v-for="item in data">
						<u-td class="u-td">{{item.garbageName}}</u-td>
						<u-td class="u-td">{{item.gcName}}</u-td>
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
				data:[],
			}
		},
		methods: {
			getGarbages(category){
				this.$request('/garbage/getGarbages?category='+category).then(resp=>{
					this.data=resp.obj
				})
			}
		},
		onLoad(option) {
			this.getGarbages(option.type);
		}
	}
</script>

<style lang="scss">
	.u-table,
	.u-td {
		border: 0 !important
	}
</style>
