<template>
	<view>
		<u-row gutter="16">
			<u-col span="12">
				<view>
					<u-search placeholder="输入进行检索" :show-action="true" :animation="true" v-model="keyword" @change="search"></u-search>
				</view>
			</u-col>
		</u-row>

		<u-row gutter="16" v-if="show">
			<u-col span="12">
				<u-table class="no-border" align="left">
					<u-tr v-if='showHead'>
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

		<view v-if="!show">
			<u-row gutter="16">
				<u-col span="12">
					<view class="border-left"><span class="u-padding-10">热门搜索</span></view>
				</u-col>
			</u-row>
			<u-row gutter="16">
				<u-col span="12">
					<u-tag class="tag" v-for="item in tags" :text="item" type="success" @click="getDetail(item)" />
				</u-col>
			</u-row>
			<u-row gutter="16">
				<u-col span="12">
					<view class="border-left"><span class="u-padding-10">Tips:</span></view>
				</u-col>
			</u-row>
			<u-row gutter="16">
				<u-col span="12">
					<view class="u-text-center">{{tips.garbageName}}属于：{{tips.gcName}}</view>
				</u-col>
			</u-row>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				garbages: [],
				showHead: false,
				emty: false,
				show: false,
				tags: [
					"苹果",
					"手机",
					"中性笔",
					"笔记本",
					"钱包",
					"洗面奶",
					"爽肤水",
					"笔记本电脑",
					"散热器"
				],
				tips:{}
			};
		},
		methods: {
			search() {
				if (this.keyword != '') {
					this.show = true;
					this.$u.api.getGarbages({
						name: this.keyword
					}).then(resp => {
						if (resp.data.obj.length > 0) {
							this.garbages = resp.data.obj;
							this.showHead = true;
							this.emty = false;
						} else {
							this.emty = true;
						}
					})
				} else {
					this.garbages = [];
					this.showHead = false;
					this.emty = false;
					this.show = false;
				}
			},
			getDetail(value) {
				console.log(value)
			},
			getTips() {
				this.$u.api.getGarbages({
					num: 1
				}).then(resp => {
					this.tips = resp.data.obj[0];
					console.log(resp.data.obj[0]);
				})
			}
		},
		onShow(){
			this.getTips();
		}
	}
</script>

<style lang="scss">
	.tag {
		margin: 0.4375em;
		display: inline-flex;
	}
</style>
