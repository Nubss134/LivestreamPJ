<template>
  <el-row :gutter="20" class="header" v-loading.fullscreen.lock="loader">
    <el-col :span="2" class="logo">
      <router-link to="/"
        ><img class="logo-image" src="../assets/logo.png" alt=""
      /></router-link>
    </el-col>
    <el-col :span="12">
      <el-menu
        class="menu"
        mode="horizontal"
        background-color="#fff"
        text-color="#000"
        active-text-color="#ffd04b"
      >
        <el-menu-item index="1"
          ><router-link to="/">Trang chủ</router-link></el-menu-item
        >
        <el-menu-item index="2"
          ><router-link to="/lives">Trực tiếp</router-link></el-menu-item
        >
        <el-menu-item index="3"
          ><router-link to="/list-category">Thể loại</router-link></el-menu-item
        >
      </el-menu>
    </el-col>
    <!-- <el-col :span="10" class="right-menu">
      <el-input
        placeholder="Enter keyword"
        v-model="keyword"
        @keyup.enter.native="search"
        style="width: 350px"
        class="right-menu-item"
      >
      </el-input>
      <span v-if="this.$store.state.user.isLogin">
        <el-popover
          class="right-menu-item"
          placement="bottom"
          width="200"
          trigger="click"
        >
          <div>
            <el-card
              style="margin: 10px"
              v-for="i in 3"
              :key="i"
              shadow="hover"
              :body-style="{ padding: '5px' }"
            >
              <div class="follow-card">
                <el-avatar
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  size="small"
                ></el-avatar>
                <span style="margin-left: 10px">
                  <p>Streamer Name</p>
                </span>
              </div>
            </el-card>
          </div>
          <i slot="reference" class="el-icon-star-off icon-header"></i>
        </el-popover>
        <i class="el-icon-time icon-header right-menu-item"></i>
        <Chat class="right-menu-item" />
        <el-popover
          popper-class="user-menu"
          placement="bottom"
          width="200"
          trigger="click"
          class="right-menu-item"
        >
          <el-menu class="el-menu-vertical-demo">
            <el-menu-item index="1" @click="openMyInfoModal = true">
              <i class="el-icon-menu"></i>
              <span>Thông tin cá nhân</span>
            </el-menu-item>
            <el-menu-item index="5" @click="openStreamModal = true">
              <i class="el-icon-setting"></i>
              <span>Streamer</span>
            </el-menu-item>
            <el-menu-item index="4" @click="logout">
              <i class="el-icon-setting"></i>
              <span>Đăng xuất</span>
            </el-menu-item>
          </el-menu>
          <el-avatar slot="reference" :src="this.$store.state.user.avatar">
          </el-avatar>
        </el-popover>
        <manage-livestream
          v-if="openStreamModal"
          :dialogVisible="openStreamModal"
          @close="openStreamModal = false"
        />
        <my-info
          v-if="openMyInfoModal"
          :dialogVisible="openMyInfoModal"
          @close="openMyInfoModal = false"
        />
      </span>
      <span v-else class="auth">
        <el-button type="primary" plain @click="dialogVisible = true"
          >Đăng nhập</el-button
        >
      </span>
    </el-col> -->
    <el-col :span="9" style="display: flex;justify-content: flex-end;">
      <el-input
        placeholder="Enter keyword"
        v-model="keyword"
        @keyup.enter.native="search"
        style="width: 450px"
        class="right-menu-item"
      >
      </el-input>
    </el-col>
    <el-col :span="1" v-if="this.$store.state.user.isLogin">
      <Chat />
    </el-col>
    <el-col :span="2" v-if="this.$store.state.user.isLogin">
      <el-popover
        popper-class="user-menu"
        placement="bottom"
        width="200"
        trigger="click"
      >
        <el-menu class="el-menu-vertical-demo">
          <el-menu-item index="1" @click="openMyInfoModal = true">
            <i class="el-icon-user"></i>
            <span>Thông tin cá nhân</span>
          </el-menu-item>
          <el-menu-item index="5" @click="openStreamModal = true">
            <i class="el-icon-setting"></i>
            <span>Livestream</span>
          </el-menu-item>
          <router-link
            to="/admin/manage-member"
            v-if="this.$store.state.user.role === 'ADMIN'"
          >
            <el-menu-item>
              <i class="el-icon-s-management"></i>
              <span>Trang quản lý</span>
            </el-menu-item>
          </router-link>
          <el-menu-item index="4" @click="logout">
            <i class="el-icon-back"></i>
            <span>Đăng xuất</span>
          </el-menu-item>
        </el-menu>
        <el-avatar slot="reference" :src="this.$store.state.user.avatar">
        </el-avatar>
      </el-popover>
      <manage-livestream
        v-if="openStreamModal"
        :dialogVisible="openStreamModal"
        @close="openStreamModal = false"
      />
      <my-info
        v-if="openMyInfoModal"
        :dialogVisible="openMyInfoModal"
        @close="openMyInfoModal = false"
      />
    </el-col>
    <el-col v-else :span="2">
      <div>
        <el-button type="primary" plain @click="dialogVisible = true"
          >Đăng nhập</el-button
        >
      </div>
    </el-col>
    <el-dialog
      :visible.sync="dialogVisible"
      width="450px"
      custom-class="login-dialog"
    >
      <el-tabs v-model="activeTabName">
        <el-tab-pane label="Đăng nhập" name="loginTab">
          <el-form
            ref="loginForm"
            :model="loginForm"
            :rules="loginRules"
            label-width="120px"
            label-position="left"
          >
            <el-form-item label="Username" prop="username">
              <el-input v-model="loginForm.username"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="password">
              <el-input type="password" v-model="loginForm.password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login">Đăng nhập</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="Đăng ký" name="signUpTab">
          <el-form
            status-icon
            ref="signUpForm"
            :model="signUpForm"
            :rules="signUpRules"
            label-width="150px"
            label-position="left"
          >
            <el-form-item label="Nick name" prop="nickName">
              <el-input v-model="signUpForm.nickName"></el-input>
            </el-form-item>
            <el-form-item label="Tên đăng nhập" prop="username">
              <el-input v-model="signUpForm.username"></el-input>
            </el-form-item>
            <el-form-item label="Mật khẩu" prop="password">
              <el-input
                type="password"
                v-model="signUpForm.password"
              ></el-input>
            </el-form-item>
            <el-form-item label="Xác nhận mật khẩu" prop="rePassword">
              <el-input
                type="password"
                v-model="signUpForm.rePassword"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="signUp">Đăng ký</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </el-row>
</template>
<script>
import { signUp, isExistUsername, logout } from "@/api/user.js";
import { Message } from "element-ui";
import ManageLivestream from "@/components/ManageLivestream";
import MyInfo from "@/components/MyInfo";
import Chat from "@/components/Chat";
import firebase from "firebase/app";
import "firebase/messaging";

export default {
  name: "Header",
  components: {
    ManageLivestream,
    MyInfo,
    Chat
  },
  data() {
    let checkUsername = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Vui lòng nhập tên đăng nhập"));
      } else {
        isExistUsername(value).then(res => {
          if (res.data) {
            callback(new Error("Tên đăng nhập đã tồn tại"));
          } else {
            callback();
          }
        });
      }
    };
    let checkRePass = (rule, value, callback) => {
      if (value !== this.signUpForm.password) {
        callback(new Error("Mật khẩu không khớp"));
      } else {
        callback();
      }
    };
    return {
      loader: false,
      openStreamModal: false,
      openMyInfoModal: false,
      dialogVisible: false,
      keyword: "",
      activeTabName: "loginTab",
      signUpForm: {
        nickName: "",
        username: "",
        password: "",
        rePassword: ""
      },
      signUpRules: {
        nickName: [
          {
            required: true,
            message: "Vui lòng nhập nick name",
            trigger: "blur"
          }
        ],
        username: [{ validator: checkUsername, trigger: "blur" }],
        password: [
          { required: true, message: "Vui lòng nhập mật khẩu", trigger: "blur" }
        ],
        rePassword: [{ validator: checkRePass, trigger: "blur" }]
      },
      loginForm: {
        username: "",
        password: ""
      },
      loginRules: {
        username: [
          {
            required: true,
            message: "Vui lòng nhập tên đăng nhập",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "Vui lòng nhập mật khẩu", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    search() {
      this.$router
        .push({ name: "Search", query: { keyword: this.keyword } })
        .catch(() => {});
    },
    async login() {
      this.loader = true;
      const messaging = firebase.messaging();
      const deviceToken = await messaging
        .getToken({
          vapidKey:
            "BEDWSXqF85zZlk5m8_8M_ypxFvsyIDPOhaQWKuz7LQVV8e6_QLEUCO0oy4Qgkqdjne1FWizMZ9z954Qrg2TMRFU"
        })
        .catch(err => {
          console.log("An error occurred while retrieving token. ", err);
        });
      this.loginForm.deviceToken = deviceToken;
      console.log("login", this.loginForm);
      this.$store.dispatch("user/login", this.loginForm).then(() => {
        this.loader = false;
        this.$router.go();
      });
    },
    signUp() {
      this.$refs.signUpForm.validate(valid => {
        if (!valid) {
          return;
        }
        let member = {
          nickName: this.signUpForm.nickName,
          username: this.signUpForm.username,
          password: this.signUpForm.password
        };
        signUp(member).then(() => {
          this.dialogVisible = false;
          Message({
            message: "Đăng ký thành công",
            type: "success",
            duration: 3 * 1000
          });
          this.$refs.signUpForm.resetFields();
        });
      });
    },
    logout() {
      logout().then(() => {
        this.$store.dispatch("user/logout").then(() => {
          this.$router.go();
        });
      });
    }
  },
  mounted() {
    const messaging = firebase.messaging();
    messaging.onMessage(payload => {
      console.log("aaaaaaa");
      this.$notify({
        title: payload.data.title,
        message: payload.data.content,
        position: "bottom-left",
        onClick: () => {
          console.log("click message");
          this.$router.push("/live/" + payload.data.id);
        }
      });
      console.log("Message receive", payload);
    });
  }
};
</script>
<style>
.follow-card {
  display: flex;
}
.icon-header {
  font-size: 1.6em;
}
.header {
  /* position: fixed;
  background-color: white;
  z-index: 2; */
  display: flex;
  align-items: center;
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.el-header {
  background-color: #fff;
}
.logo-image {
  padding-left: 10px;
  max-width: 100%;
  max-height: 100%;
}
.menu {
  border-bottom: none !important;
}
.auth {
  display: inline-block;
}
.user-menu {
  padding: 0px;
}
.right-menu {
  display: flex;
  justify-content: flex-end;
  padding-right: 20px !important;
}
.right-menu-item {
  margin-right: 30px;
}
</style>
