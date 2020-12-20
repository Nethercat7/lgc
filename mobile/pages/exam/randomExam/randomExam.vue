<template>
	<view>
		<!-- 封面图片 -->
		<view class="exam-header"></view>
		<!-- 内容 -->
		<view style="padding:50rpx" class="exam-content">
			<view class="exam-question">
				<u-tag :text="count<5?count+1+'/'+5:count+'/'+5" shape="circle" type="info" style="height:30rpx" />
				<span style="padding-left: 15rpx;">
					<span style="font-size: 40rpx;">{{data[count].garbageName}}</span>
					<span>属于什么垃圾？</span>
				</span>
			</view>

			<view>
				<u-row>
					<u-col span="6" v-for="item in categories" :key="item.gcId">
						<button class="exam-answer" :type="item.gcType" @click="getNext(item.gcName)">{{item.gcName}}</button>
					</u-col>
				</u-row>
			</view>

			<u-modal v-model="showModal" title="答错啦" :content="modalContent" @confirm="submit"></u-modal>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: [{
					garbageName: 'temp'
				}],
				count: 0,
				total: 0,
				wrong: [],
				categories: [],
				showModal: false,
				modalContent: 'Content'
			};
		},
		methods: {
			getNext(value) {
				if (this.count < this.data.length) {
					//答错时的操作
					if (value != this.data[this.count].gcName) {
						this.data[this.count].errAnswer = value;
						this.wrong.push(this.data[this.count]);
						this.showModal = true;
						this.modalContent=this.data[this.count].garbageName+' 属于 '+this.data[this.count].gcName
					}
					this.count++;
					
					//题目全部答完时的操作
					if (this.total < this.data.length - 1) {
						this.total++;
					} else {
						if(!this.showModal){
							this.$jump.redirect('/pages/exam/submitResult/submitResult?answer=' + encodeURIComponent(JSON.stringify(this.wrong))+'&garbages='+encodeURIComponent(JSON.stringify(this.data)));
						}
					}
				}
			},
			getGarbages() {
				this.$u.api.getGarbages({
					num: 5
				}).then(resp => {
					this.data = resp.data.obj;
				})
			},
			getCategories() {
				this.$u.api.getGarbageCategories().then(resp => {
					this.categories = resp.data.obj
				})
			},
			submit(){
				if(this.count==5){
					this.$jump.redirect('/pages/exam/submitResult/submitResult?answer=' + encodeURIComponent(JSON.stringify(this.wrong))+'&garbages='+encodeURIComponent(JSON.stringify(this.data)));
				}
			}
		},
		onLoad() {
			this.getGarbages();
			this.getCategories();
		}
	}
</script>

<style lang="scss">
	.exam-header {
		width: 100%;
		height: 300rpx;
		background-color: #19be6b;
	}

	.exam-answer {
		height: 300rpx;
		margin-bottom: 15rpx;
		line-height: 300rpx;
		color: #FFFFFF;
	}
</style>