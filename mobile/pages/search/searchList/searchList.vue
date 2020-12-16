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
					<u-tr v-if='show'>
						<u-th>名称</u-th>
						<u-th>类别</u-th>
					</u-tr>
					<u-tr v-for='item in garbages'>
						<u-td>{{item.garbageName}}</u-td>
						<u-td>{{item.gcName}}</u-td>
					</u-tr>
					<u-tr v-if='emty'>
						<u-td>未找到相关物品信息</u-td>
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
				show:false,
				emty:false
			};
		},
		methods: {
			search() {
				if(this.keyword != ''){
					this.$u.api.getGarbages({
						name:this.keyword
					}).then(resp=>{
						if(resp.data.obj.length>0){
							this.garbages = resp.data.obj;
							this.show=true;
							this.emty=false;
						}else{
							this.emty=true;
						}
					})
				}else{
					this.garbages=[];
					this.show=false;
					this.emty=false;
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
