import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
import actions from './actions'

Vue.use(Vuex)

const state = {
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    loginForm: {
        userID: "",
        userPsw: "",
        randString: ""
    }
}

export default new Vuex.Store({
    state,
    mutations,
    actions,
    modules: {}
})