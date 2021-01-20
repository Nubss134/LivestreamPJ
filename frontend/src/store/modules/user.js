import { login } from "@/api/user";
import { setToken, removeToken } from "@/utils/auth";
import { Message } from "element-ui";
const state = {
  isLogin: false,
  id: null,
  token: "",
  username: "",
  avatar: "",
  role: ""
};

const mutations = {
  SET_INFO: (state, userInfo) => {
    state.id = userInfo.id;
    state.isLogin = userInfo.isLogin;
    state.username = userInfo.username;
    state.token = userInfo.token;
    state.avatar = userInfo.avatar;
    state.role = userInfo.authorities[0].authority;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  }
};

const actions = {
  login({ commit }, userInfo) {
    return new Promise((resolve, reject) => {
      login(userInfo)
        .then(response => {
          const { data } = response;
          if (!data) {
            Message({
              message: "Tên đăng nhập hoặc mật khẩu không đúng",
              type: "error",
              duration: 3 * 1000
            });
          } else {
            data.isLogin = true;
            console.log(data.authorities[0].authority);
            commit("SET_INFO", data);
            setToken(data.token);
          }
          resolve();
        })
        .catch(error => {
          reject(error);
        });
    });
  },
  logout({ commit }) {
    removeToken("user");
    commit("SET_INFO", {
      id: null,
      isLogin: false,
      username: null,
      token: null,
      avatar: null,
      authorities: [
        {
          authorities: null
        }
      ]
    });
  },
  updateAvatar({ commit }, avatar) {
    commit("SET_AVATAR", avatar);
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
