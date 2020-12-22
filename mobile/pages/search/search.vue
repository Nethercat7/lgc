<template>
	<view>
		<view class="search-bar">
			<view>
				<view class="search-icon">
					<u-icon name="search"></u-icon>
				</view>
				<view>
					<input type="text" class="search" placeholder="输入进行检索" v-model="keyword" @input="search">
				</view>
				<u-row class="search-btn">
					<u-col span="6">
						<button @click="showModal=true">
							<u-icon name="camera" style="margin-right: 10rpx;"></u-icon>拍照搜索
						</button>
					</u-col>
					<u-col span="6">
						<button @click="showModal=true">
							<u-icon name="mic" style="margin-right: 10rpx;"></u-icon>语音搜索
						</button>
					</u-col>
				</u-row>
			</view>
		</view>

		<view style="padding:30rpx;">
			<view v-if="show">
				<view v-for="item,index in garbages" :key="item.garbageId" class="card u-font-xl">
					<span>{{item.garbageName}}</span>
					<u-icon class="favorite" :name="item.inFavorite?'star-fill':'star'" :custom-style="item.inFavorite?style:''"
					 @click="addFavorite(index)"></u-icon>
					<u-tag class="gc-tag" :text="item.gcName" :type="item.gcType"></u-tag>
				</view>
				<view v-if="emty">
					<view class="card u-font-xl u-text-center">
						未找到相关数据
					</view>
				</view>
			</view>

			<view v-if="!show">
				<view>
					<view class="border-left"><span class="u-padding-10">Tips:</span></view>
					<view class="u-text-center">{{tips.garbageName}}属于：{{tips.gcName}}</view>
				</view>
			</view>
		</view>
		<u-modal v-model="showModal" content="此功能暂未开放"></u-modal>
		<u-toast ref="toast" />
	</view>
</template>

<script>
	import storage from '@/common/storage.js';
	export default {
		data() {
			return {
				keyword: '',
				garbages: [],
				emty: false,
				show: false,
				tips: {},
				showModal: false,
				style: {
					"color": 'rgba(25,190,107,.5)'
				}
			};
		},
		methods: {
			search() {
				if (this.keyword != '') {
					this.show = true;
					if (storage.getUser() != null) {
						//登录用户使用这个API
						this.$u.api.getGarbageWithFavorite({
							name: this.keyword,
							userId: storage.getUser().userId
						}).then(resp => {
							let data = resp.data.obj;
							if (data.length > 0) {
								/* tag样式转换 */
								for (let i = 0; i < data.length; i++) {
									if (data[i].gcType === 'kitchen') {
										data[i].gcType = 'success'
									} else if (data[i].gcType === 'hazardous') {
										data[i].gcType = 'error'
									} else if (data[i].gcType === 'other') {
										data[i].gcType = 'info'
									} else if (data[i].gcType === 'recyclable') {
										data[i].gcType = 'primary'
									}
								}
								this.emty = false;
							} else {
								this.emty = true;
							}
							this.garbages = data;
						})
					} else {
						//未登录用户使用这个API
						this.$u.api.getGarbages({
							name: this.keyword,
						}).then(resp => {
							let data = resp.data.obj;
							if (data.length > 0) {
								/* tag样式转换 */
								for (let i = 0; i < data.length; i++) {
									if (data[i].gcType === 'kitchen') {
										data[i].gcType = 'success'
									} else if (data[i].gcType === 'hazardous') {
										data[i].gcType = 'error'
									} else if (data[i].gcType === 'other') {
										data[i].gcType = 'info'
									} else if (data[i].gcType === 'recyclable') {
										data[i].gcType = 'primary'
									}
								}
								this.emty = false;
							} else {
								this.emty = true;
							}
							this.garbages = data;
						})
					}
				} else {
					this.garbages = [];
					this.emty = false;
					this.show = false;
				}
			},
			getTips() {
				this.$u.api.getGarbages({
					num: 1
				}).then(resp => {
					this.tips = resp.data.obj[0];
				})
			},
			addFavorite(index) {
				if (storage.getUser() == null) {
					this.$msg.sendError(this, "登录之后才能收藏哦");
				} else {
					if(this.garbages[index].inFavorite){
						this.$u.api.delFavorite({
							userId:storage.getUser().userId,
							garbageId:this.garbages[index].garbageId
						}).then(res=>{
							if(res.data.code==1){
								this.garbages[index].inFavorite=false;
							}
							this.$msg.send(this,res.data.msg,res.data.type)
						})
					}else{
						this.$u.api.addFavorite({
							userId:storage.getUser().userId,
							garbageId:this.garbages[index].garbageId
						}).then(res=>{
							if(res.data.code==1){
								this.garbages[index].inFavorite=true;
							}
							this.$msg.send(this,res.data.msg,res.data.type)
						})
					}
				}
			}
		},
		onShow() {
			this.getTips();
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

	.search-icon {
		display: inline;
		float: left;
		height: 70rpx;
		line-height: 70rpx;
		background-color: #FFFFFF;
		padding-left: 10rpx;
		padding-right: 10rpx;
		border-top-left-radius: 7rpx;
		border-bottom-left-radius: 7rpx;
	}

	.search {
		width: 648rpx;
		height: 70rpx;
		background-color: #FFFFFF;
		text-indent: 15rpx;
		border-top-right-radius: 7rpx;
		border-bottom-right-radius: 7rpx;
	}

	.search-btn button {
		color: #FFFFFF;
		background-color: rgba($color: #ffffff, $alpha: .1);
	}

	.search-btn button:active {
		color: #FFFFFF;
		background-color: rgba($color: #ffffff, $alpha: .5);
	}

	.search-bar {
		height: 250rpx;
		background-color: $u-type-success;
	}

	.search-bar>view {
		padding: 30rpx;
	}

	.favorite {
		float: right;
		height: 3em;
		padding-right: 30rpx;
	}
</style>
