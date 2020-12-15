<template>
	<view class="wrap">
		<u-row gutter="16">
			<u-col span="12">
				<h1>{{posts.title}}</h1>
				<hr />
			</u-col>
		</u-row>

		<u-row gutter="16">
			<u-col span="12" class='posts'>
				<view v-html="posts.postsContent"></view>
			</u-col>
		</u-row>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				posts: '',
			};
		},
		methods: {
			
		},
		onLoad(option) {
			this.posts=JSON.parse(decodeURIComponent(option.obj));
		}
	}
</script>

<style scoped>
	.posts>>>img {
		width: 100%;
	}
</style>
