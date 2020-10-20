import router from '../router/index'
import {
  CHANGE_LOGIN,
  CHANGE_LOGOUT,
  CHECK_TOKEN,
  SUBUSER,
  GET_USERID
} from './mutation-types'

export default {
  [CHANGE_LOGIN](state, user) {
    state.Authorization = user.Authorization;
    localStorage.setItem('Authorization', user.Authorization)
  },
  [CHANGE_LOGOUT](state) {
    state.Authorization = '';
    localStorage.removeItem('Authorization');
  },
  [CHECK_TOKEN](state) {
    let token = localStorage.getItem('Authorization');
    if (token === '' || token === null) {
      alert('登录已失效，请重新登录');
      router.push('/login');
      return false;
    }
    return true;
  },
  [SUBUSER](state, user) {
    // console.log(user);
    state.loginForm = user;
  },
  [GET_USERID](state, userId) {
    localStorage.setItem('USERID', userId)
  }
}
