<template>
	<view class="wrap">
		<u-swiper :list="list" :title="true"></u-swiper>
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
				<u-button :custom-style="green" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=1')">厨余垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="blue" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=2')">可回收垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="red" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=3')">有害垃圾</u-button>
			</u-col>
			<u-col span="3">
				<u-button :custom-style="gray" size="default" @click="navigateTo('/pages/index/catalogList/catalogList?type=4')">其他垃圾</u-button>
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
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//轮播图数据
				list: [{
						image: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},
					{
						image: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					},
				],
				//知识科普数据
				data: [{
					image: 'https://cdn.uviewui.com/uview/example/fade.jpg',
					title: '震惊！某高校学生因为扔垃圾时未进行垃圾分类被扔进有害垃圾桶'
				},{
					image: 'https://cdn.uviewui.com/uview/example/fade.jpg',
					title: '震惊！某高校学生因为扔垃圾时未进行垃圾分类被扔进有害垃圾桶'
				},{
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

		},
		methods: {
			navigateTo(url) {
				uni.navigateTo({
					url: url
				})
			},
			test() {
				console.log("clicked")
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
