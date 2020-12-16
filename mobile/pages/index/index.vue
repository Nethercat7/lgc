<template>
	<view class="wrap">
		<!-- 轮播图 -->
		<u-swiper :list="announcementList" :title="true" @click='goAnnouncement'></u-swiper>
		
		<!-- 分类检索 Start -->
		<u-row gutter="16">
			<u-col span="12">
				<view class="border-left">
					<span class="u-padding-10">按照类别检索</span>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="3">
				<u-button :custom-style="green" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=厨余垃圾')">厨余垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="blue" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=可回收垃圾')">可回收垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="red" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=有害垃圾')">有害垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="gray" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=其他垃圾')">其他垃圾</u-button>
			</u-col>
		</u-row>
		<!-- 分类检索 End -->
		
		<!-- 知识科普 Start -->
		<u-row gutter="16">
			<u-col span="12">
				<view class="border-left">
					<span class="u-padding-10">知识科普</span>
				</view>
			</u-col>
		</u-row>
		<u-row gutter="16">
			<u-col span="12">
				<view class="post-card" v-for="(item,index) in knowledgePopularization" @click="goKnowledgePopularization(index)">
					<view>
						<u-image width="100%" height="7em" :src="item.image"></u-image>
					</view>
					<view class="post-title u-line-1 u-font-lg u-padding-10">
						{{item.title}}
					</view>
				</view>
			</u-col>
		</u-row>
		<!-- 知识科普 End -->
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
				//button按钮样式
				green: {
					color: '#fff',
					height: '3.75em',
					backgroundColor: 'rgb(41, 152, 86) !important'
				},
				blue: {
					color: '#fff',
					height: '3.75em',
					backgroundColor: 'rgb(71, 154, 214) !important'
				},
				red: {
					color: '#fff',
					height: '3.75em',
					backgroundColor: 'rgb(220, 108, 125) !important'
				},
				gray: {
					color: '#fff',
					height: '3.75em',
					backgroundColor: 'rgb(145, 152, 158) !important'
				}
			}
		},
		methods: {
			navigateTo(url) {
				uni.navigateTo({
					url:url
				})
			},
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
				this.navigateTo('/pages/posts/posts?obj=' + encodeURIComponent(JSON.stringify(this.announcementList[index])));
			},
			goKnowledgePopularization(index) {
				this.navigateTo('/pages/posts/posts?obj=' + encodeURIComponent(JSON.stringify(this.knowledgePopularization[index])));
			}
		},
		onLoad() {
			this.getAnnouncement();
			this.getKnowledgePopularization();
			console.log(this.announcementList);
		}
	}
</script>


<style scoped lang="scss">
	.post-card {
		border: 1px solid $lgc-post-card-border;
		margin-bottom: 10px;
	}
</style>
