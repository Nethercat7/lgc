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
					<u-tr class="u-tr" v-for="(item,index) in data" :key="item.index">
						<u-td class="u-td">
							{{item.index}}
						</u-td>
						<u-td class="u-td">
							{{item.name}}
						</u-td>
						<u-td class="u-td">
							{{item.score}}
						</u-td>
						<u-td class="u-td">
							{{item.title}}
						</u-td>
					</u-tr>
					<view class="border-left">
						<span class="u-padding-10">宁的排名</span>
					</view>
					<u-tr class="u-tr">
						<u-td class="u-td">
							{{userRange.index}}
						</u-td>
						<u-td class="u-td">
							{{userRange.name}}
						</u-td>
						<u-td class="u-td">
							{{userRange.score}}
						</u-td>
						<u-td class="u-td">
							{{userRange.title}}
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
				data: [{
						name: "nethercat1",
						score: 777,
						title: "垃圾大王",
						index: 1
					},
					{
						name: "nethercat2",
						score: 777,
						title: "垃圾大王",
						index: 2
					},
					{
						name: "nethercat3",
						score: 777,
						title: "垃圾大王",
						index: 3
					},
					{
						name: "nethercat4",
						score: 777,
						title: "垃圾大王",
						index: 4
					},
					{
						name: "nethercat5",
						score: 777,
						title: "垃圾大王",
						index: 5
					},
					{
						name: "nethercat6",
						score: 777,
						title: "垃圾大王",
						index: 6
					},
					{
						name: "nethercat7",
						score: 777,
						title: "垃圾大王",
						index: 7
					},
					{
						name: "nethercat8",
						score: 777,
						title: "垃圾大王",
						index: 8
					},
					{
						name: "nethercat9",
						score: 777,
						title: "垃圾大王",
						index: 9
					},
					{
						name: "nethercat10",
						score: 777,
						title: "垃圾大王",
						index: 10
					},
				],
				userRange:{
					name:"nethercat7",
					score:777,
					title:"垃圾大王",
					index:7
				}
			};
		}
	}
</script>

<style lang="scss">
	.u-table,
	.u-td {
		border: 0 !important
	}
</style>
