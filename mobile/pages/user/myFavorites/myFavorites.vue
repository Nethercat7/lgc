<template>
	<view class="wrap">
		<view v-if="!emty" v-for="item,index in garbages" :key="item.garbageId" class="card u-font-xl">
			<span>{{item.garbageName}}</span>
			<u-icon class="favorite" name="star-fill" :custom-style="style" @click="delFavorite(index)"></u-icon>
			<u-tag class="gc-tag" :text="item.gcName" :type="item.gcType"></u-tag>
		</view>
		<view v-if="emty">
			<view class="card u-font-xl u-text-center">
				暂无已收藏的垃圾分类
			</view>
		</view>
		<u-toast ref="toast"></u-toast>
	</view>
</template>

<script>
	import storage from '@/common/storage.js'
	export default {
		data() {
			return {
				garbages: [],
				style: {
					"color": 'rgba(25,190,107,.5)'
				},
				emty: false
			};
		},
		methods: {
			delFavorite(index) {
				this.$u.api.delFavorite({
					userId: storage.getUser().userId,
					garbageId: this.garbages[index].garbageId
				}).then(res => {
					if (res.data.code == 1) {
						//将物品移除至数组
						this.garbages.splice(index, 1);
					}
					this.$msg.send(this, res.data.msg, res.data.type);
				})
			},
			getFavorite() {
				this.$u.api.getGarbages({
					userId: storage.getUser().userId
				}).then(res => {
					this.garbages = res.data.obj;
					if (this.garbages.length == 0) this.emty = true;
				})
			}
		},
		onLoad() {
			this.getFavorite();
		}
	}
</script>

<style lang="scss">
	.card {
		width: 100%;
		height: 3em;
		padding-left: 0.5em;
		margin-bottom: 1em;
		line-height: 3em;
		background-color: white;
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.3);
	}

	.gc-tag {
		float: right;
		padding-right: 30rpx;
		position: relative;
		bottom: 5rpx;
	}

	.favorite {
		float: right;
		height: 3em;
		padding-right: 30rpx;
	}
</style>
