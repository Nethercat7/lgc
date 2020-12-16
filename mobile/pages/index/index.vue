<template>
	<view class="wrap">
		<u-swiper :list="postsList" :title="true" @click='goPosts'></u-swiper>
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
				<view class="post-card" v-for="(item,index) in data">
					<view class="post-pic">
						<u-image :src="item.image"></u-image>
					</view>
					<view class="post-title u-line-1 u-font-lg u-padding-10">
						{{item.title}}
					</view>
				</view>
			</u-col>
		</u-row>
		<!-- 知识科普 End -->
		<u-row gutter="16">
			<u-col span="12">
				<u-button :custom-style="gray" size="default" @click="navigateTo('/pages/posts/posts')">其他垃圾</u-button>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//轮播图数据
				postsList: [],
				//知识科普数据
				data: [{
					image: 'https://cdn.uviewui.com/uview/example/fade.jpg',
					title: '震惊！某高校学生因为扔垃圾时未进行垃圾分类被扔进有害垃圾桶'
				}, {
					image: 'https://cdn.uviewui.com/uview/example/fade.jpg',
					title: '震惊！某高校学生因为扔垃圾时未进行垃圾分类被扔进有害垃圾桶'
				}, {
					image: 'https://cdn.uviewui.com/uview/example/fade.jpg',
					title: '震惊！某高校学生因为扔垃圾时未进行垃圾分类被扔进有害垃圾桶'
				}],
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
		onLoad() {
			this.getPosts();
		},
		methods: {
			navigateTo(url) {
				uni.navigateTo({
					url: url
				})
			},
			getPosts() {
				this.$request('/postsMgt/getPosts').then(resp => {
					this.postsList = resp.obj;
					console.log(this.postsList);
				})
			},
			goPosts(index){
				this.navigateTo('/pages/posts/posts?obj='+encodeURIComponent(JSON.stringify(this.postsList[index])));
			}
		}
	}
</script>


<style scoped lang="scss">
	.post-card {
		border: 1px solid $lgc-post-card-border;
		margin-bottom: 10px;
	}

	.post-pic .u-image {
		height: 7em !important;
	}
</style>
