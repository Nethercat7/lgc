import Vue from 'vue'
import App from './App'
import uView from "uview-ui";
import request from "common/request.js"
import storage from "common/storage.js"

Vue.use(uView);

Vue.config.productionTip = false

Vue.prototype.$request=request
Vue.prototype.$storage=storage

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
