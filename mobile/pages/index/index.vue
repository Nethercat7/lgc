<template>
	<view class="wrap">
		<!-- 轮播图 -->
		<view>
			<u-swiper :list="announcementList" :title="true" @click='goAnnouncement'></u-swiper>
		</view>

		<!-- 分类检索 -->
		<view>
			<view class="border-left">
				<span class="u-padding-10">按照类别检索</span>
			</view>
			<u-row>
				<u-col v-for="item in categories" span="3">
					<button class="category-btn shadow" :type="item.gcType" @click="$jump.navigate('/pages/index/catalogList/catalogList?type='+item.gcName)">{{item.gcName}}</button>
				</u-col>
			</u-row>
		</view>

		<!-- 知识科普 -->
		<view>
			<view class="border-left">
				<span class="u-padding-10">知识科普</span>
			</view>
			<view>
				<view style="margin-bottom: 0.9375em;" v-for="(item,index) in data" @click="goKnowledgePopularization(index)">
					<view>
						<u-image width="100%" height="7em" :src="item.image"></u-image>
					</view>
					<view class="u-line-1 u-font-lg u-padding-10" style="background-color: #FFFFFF;">
						{{item.title}}
					</view>
				</view>
				<u-loadmore :status="status" />
			</view>
		</view>
	</view>
</template>

<script>
	import storage from '../../common/storage.js';

	export default {
		data() {
			return {
				//轮播图数据
				announcementList: [],
				//知识科普数据
				knowledgePopularization: [],
				categories: [],
				status: 'loadmore',
				data: []
			}
		},
		methods: {
			getAnnouncement() {
				this.$u.api.getPosts({
					id: '459039470344601600'
				}).then(resp => {
					this.announcementList = resp.data.obj;
				})
			},
			getKnowledgePopularization() {
				this.$u.api.getPosts({
					id: '459039281823219712'
				}).then(resp => {
					this.knowledgePopularization = resp.data.obj;
					/* 将数据全部获取后，初始加载时只加载前3条数据 */
					for (let i = 0; i < this.knowledgePopularization.length; i++) {
						if (i == 3) break;
						this.data.push(this.knowledgePopularization[i]);
					}
				})
			},
			goAnnouncement(index) {
				this.$jump.navigate('/pages/posts/posts?obj=' + encodeURIComponent(JSON.stringify(this.announcementList[index])));
			},
			goKnowledgePopularization(index) {
				this.$jump.navigate('/pages/posts/posts?obj=' + encodeURIComponent(JSON.stringify(this.knowledgePopularization[
					index])));
			},
			getCategories() {
				this.$u.api.getGarbageCategories().then(resp => {
					this.categories = resp.data.obj;
				})
			}
		},
		onLoad() {
			this.getAnnouncement();
			this.getKnowledgePopularization();
			this.getCategories();
		},
		/* 知识科普-加载更多 */
		onReachBottom() {
			this.status = 'loading';
			setTimeout(() => {
				let data = this.data;
				let knowledgePopularization = this.knowledgePopularization;
				let count = 0;
				for (let i = data.length; i < knowledgePopularization.length; i++) {
					if (count == 3) break;
					data.push(knowledgePopularization[i]);
					count++;
				}
				if (data.length == knowledgePopularization.length) {
					this.status = "nomore";
				}
			}, 1000)
		}
	}
</script>


<style scoped lang="scss">
	.wrap>view {
		margin-bottom: 1.875em;
	}

	.category-btn {
		height: 150rpx;
		line-height: 150rpx;
		padding-left: 10rpx;
		padding-right: 10rpx;
		font-size: 27rpx;
		color: #FFFFFF;
		margin-bottom: 30rpx;
	}

	/deep/ .u-image image {
		border-top-left-radius: 15rpx !important;
		border-top-right-radius: 15rpx !important;
	}

	/deep/ .u-swiper-wrap {
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.7);
	}
</style>
