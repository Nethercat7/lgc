import Vue from 'vue';
import App from './App';
import uView from "uview-ui";
import httpInterceptor from "@/common/http.interceptor.js";
import httpApi from '@/common/http.api.js';

Vue.use(uView);

Vue.config.productionTip = false;

App.mpType = 'app';

const app = new Vue({
	...App
});

//启用拦截器
Vue.use(httpInterceptor, app);
//启用API集中管理
Vue.use(httpApi, app);

app.$mount();
