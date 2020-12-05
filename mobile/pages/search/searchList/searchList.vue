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
					<u-tr v-for="item in data">
						<u-td>{{item.name}}</u-td>
						<u-td>[{{item.type}}]</u-td>
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
				data: [{
					name: "果皮",
					type: "厨余垃圾"
				},{
					name: "瓜子壳",
					type: "厨余垃圾"
				},{
					name: "花生壳",
					type: "厨余垃圾"
				},{
					name: "计算机",
					type: "可回收垃圾"
				},{
					name: "打印机",
					type: "可回收垃圾"
				},{
					name: "复印机",
					type: "可回收垃圾"
				},{
					name: "废充电电池",
					type: "有害垃圾"
				},{
					name: "扣子电池",
					type: "有害垃圾"
				},{
					name: "废荧光灯管",
					type: "有害垃圾"
				},{
					name: "卫生巾",
					type: "其他垃圾"
				},{
					name: "纸尿裤",
					type: "其他垃圾"
				},{
					name: "餐巾纸",
					type: "其他垃圾"
				}
				]
			};
		},
		methods: {
			search() {
				console.log(this.keyword);
			}
		}
	}
</script>

<style lang="scss">
	.u-table,
	.u-td{
		border:0 !important
	}
</style>
