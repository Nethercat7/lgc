<template>
	<view :class="style">
		<view class="category-info">
			<view class="category-title">{{title}}</view>
			<view class="category-description">
				{{description}}
				<span style="display: block; padding-top: 50rpx;">属于{{title}}的有:</span>
			</view>
		</view>

		<view class="category-content">
			<view v-for="item in data">
				<view class="garbage u-border-bottom">{{item.garbageName}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: [],
				title: '',
				description: '',
				style: ''
			}
		},
		methods: {
			getGarbages(category) {
				this.$u.api.getGarbages({
					category: category
				}).then(resp => {
					this.data = resp.data.obj
				})
			}
		},
		onLoad(option) {
			this.getGarbages(option.type);
			this.title = option.type;
			if (option.type === '厨余垃圾') {
				this.style = 'category-kitchen';
				this.description =
					'厨余垃圾是指居民日常生活及食品加工、饮食服务、单位供餐等活动中产生的垃圾，包括丢弃不用的菜叶、剩菜、剩饭、果皮、蛋壳、茶渣、骨头等，其主要来源为家庭厨房、餐厅、饭店、食堂、市场及其他与食品加工有关的行业。';
			} else if (option.type === '有害垃圾') {
				this.style = 'category-hazardous';
				this.description =
					'有害垃圾指对人体健康或者自然环境造成直接或者潜在危害的生活废弃物。常见的有害垃圾包括废灯管、废油漆、杀虫剂、废弃化妆品、过期药品、废电池、废灯泡、废水银温度计等，有害垃圾需按照特殊正确的方法安全处理。';
			} else if (option.type === '其他垃圾') {
				this.style = 'category-other';
				this.description = '其他垃圾指的是出了可回收垃圾、处于垃圾、有害垃圾之外的垃圾，包括砖瓦陶瓷、渣土、卫生间废纸、纸巾等各种难以进行回收的垃圾。';
			} else {
				this.style = 'category-recyclable';
				this.description =
					'可回收垃圾就是可以再生循环的垃圾。本身或材质可再利用的纸类、硬纸板、玻璃、塑料、金属、人造合成材料包装，与这些材质有关的如：报纸、杂志、广告单及其它干净的纸类等皆可回收。另外包装上有绿色标章是属于要付费的双系统，亦属于可回收垃圾。';
			}
		}
	}
</script>

<style lang="scss">
	.category-kitchen {
		min-height: 100vh;
		background-color: #19be6b;
	}

	.category-hazardous {
		min-height: 100vh;
		background-color: #fa3534;
	}


	.category-other {
		min-height: 100vh;
		background-color: #909399;
	}

	.category-recyclable {
		min-height: 100vh;
		background-color: #2979ff;
	}

	.category-title {
		color: white;
		font-size: 70rpx;
		text-align: center;
		padding-top: 30rpx;
	}

	.category-description {
		color: white;
		font-size: 30rpx;
		padding: 60rpx;
	}

	.category-content {
		width: 650rpx;
		margin: auto;
		background-color: #FFFFFF;
		border-radius: 30rpx;
	}

	.garbage {
		padding-top: 30rpx;
		padding-bottom: 30rpx;
		margin-left: 30rpx;
		margin-right: 30rpx;
		font-size: 40rpx;
	}
</style>
