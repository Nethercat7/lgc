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
			<view class="shadow" style="margin-bottom: 0.9375em;" v-for="(item,index) in knowledgePopularization" @click="goKnowledgePopularization(index)">
				<view>
					<u-image width="100%" height="7em" :src="item.image"></u-image>
				</view>
				<view class="post-title u-line-1 u-font-lg u-padding-10">
					{{item.title}}
				</view>
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
				categories: []
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

	/deep/ .u-swiper-wrap {
		box-shadow: 0 0 8px 0 rgba($color: #000000, $alpha: 0.7);
	}
</style>
