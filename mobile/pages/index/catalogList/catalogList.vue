<template>
	<view>
		<u-row gutter="16">
			<u-col span="12">
				<u-table>
					<u-tr class="u-tr" v-for="item in data">
						<u-td class="u-td">{{item}}</u-td>
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
				data1:[
					"果皮",
					"瓜子壳",
					"花生壳",
				],
				data2:[
					"计算机",
					"打印机",
					"复印机"
				],
				data3:[
					"镉镍电池",
					"氧化汞电池",
					"铅蓄电池"
				],
				data4:[
					"纸尿裤",
					"餐巾纸",
					"卫生纸",
				]
			}
		},
		methods: {

		},
		onLoad(option) {
			if(option.type==1){
				this.data=this.data1
			}else if(option.type==2){
				this.data=this.data2
			}else if(option.type==3){
				this.data=this.data3
			}else if(option.type==4){
				this.data=this.data4
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
