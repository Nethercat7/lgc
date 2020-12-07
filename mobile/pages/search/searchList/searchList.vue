<template>
	<view>
		<u-row gutter="16">
			<u-col span="12">
				<view>
					<u-search placeholder="点击进行搜索" :show-action="true" :animation="true" v-model="keyword" @change="search"></u-search>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<u-table class="no-border" align="left">
					<u-tr v-for='item in garbages'>
						<u-td>{{item.garbageName}}</u-td>
						<u-td>{{item.gcName}}</u-td>
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
				keyword: '',
				garbages: [],
			};
		},
		methods: {
			search() {
				if(this.keyword != ''){
					this.$request('/garbage/getGarbages?name=' + this.keyword).then(resp => {
						this.garbages = resp.obj
					})
				}else{
					this.garbages=[];
				}
			}
		}
	}
</script>

<style lang="scss">
	.u-table,
	.u-td {
		border: 0 !important
	}
</style>
