<template>
  <div>
    <el-dialog :visible.sync="show" width="30%" :before-close="handleClose">
      <div>
        <el-tabs v-model="activeName">
          <el-tab-pane label="Cá nhân" name="first">
            <el-row>
              <el-col>
                <!-- <el-avatar
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  :size="150"
                  shape="square"
                ></el-avatar> -->
              </el-col>
              <el-col :span="24">
                <div style="text-align: center">
                  <Upload
                    style="height: 150px; width: 150px; margin: 0 auto"
                    @uploadSuccess="personal.avatar = $event"
                    :oldImage="personal.avatar"
                  ></Upload>
                </div>

                <el-form
                  status-icon
                  label-width="100px"
                  :model="personal"
                  :rules="rules"
                  ref="personal"
                >
                  <el-form-item label="Nick name" prop="nickName">
                    <el-input v-model="personal.nickName"></el-input>
                  </el-form-item>
                  <el-form-item label="Điện thoại" prop="phone">
                    <el-input v-model="personal.phone"></el-input>
                  </el-form-item>
                  <el-form-item label="Email" prop="email">
                    <el-input v-model="personal.email"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="updatePersonalInfo">
                      Cập nhật</el-button
                    >
                  </el-form-item>
                </el-form>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="Lịch sử" name="third">
            <div class="block histories">
              <el-timeline v-for="(history, i) in listHistory" :key="i">
                <el-timeline-item
                  :timestamp="history.updatedTime"
                  placement="top"
                >
                  <el-card>
                    <div class="history-card">
                      <el-avatar
                        :src="history.avatar"
                        :size="80"
                        shape="square"
                      ></el-avatar>
                      <span style="margin-left: 10px">
                        <h4>{{ history.streamerName }}</h4>
                        <p>{{ history.title }}</p>
                      </span>
                    </div>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Theo dõi" name="fourth">
            <div v-for="(follow, i) in listFollow" :key="i" class="follow-item">
              <el-avatar :src="follow.avatar" :size="50"></el-avatar>
              <span>{{ follow.nickName }}</span>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getPersonalInfo, updatePersonalInfo, getListFollow } from "@/api/user";
import { Message } from "element-ui";
import { getHistory } from "@/api/history";
import Upload from "@/components/Upload";

export default {
  name: "MyInfo",
  components: { Upload },
  props: ["dialogVisible"],
  data() {
    return {
      personal: {
        avatar: "",
        nickName: "",
        phone: "",
        email: ""
      },
      rules: {
        nickName: [
          {
            required: true,
            message: "Vui lòng nhập tên hiển thị",
            trigger: "blur"
          }
        ],
        phone: [
          {
            required: true,
            message: "Vui lòng nhập số điện thoại",
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "Vui lòng nhập email",
            trigger: "blur"
          }
        ]
      },
      activeName: "first",
      listHistory: [],
      listFollow: []
    };
  },
  computed: {
    show: {
      get() {
        return this.dialogVisible;
      },
      set() {
        this.$emit("close");
      }
    }
  },
  methods: {
    handleClose() {
      this.show = false;
    },
    getPersonalInfo() {
      const id = this.$store.state.user.id;
      getPersonalInfo(id).then(res => {
        this.personal.nickName = res.data.nickName;
        this.personal.phone = res.data.phone;
        this.personal.email = res.data.email;
        this.personal.avatar = res.data.avatar;
      });
    },
    updatePersonalInfo() {
      this.$refs.personal.validate(valid => {
        if (!valid) {
          return;
        }
        updatePersonalInfo(this.personal).then(() => {
          this.$store.dispatch("user/updateAvatar", this.personal.avatar);
          Message({
            message: "Cập nhật thành công",
            type: "success",
            duration: 3 * 1000
          });
        });
      });
    },
    getHistory() {
      getHistory().then(res => {
        this.listHistory = res.data;
      });
    },
    getListFollow() {
      getListFollow().then(res => {
        this.listFollow = res.data;
      });
    }
  },
  mounted() {
    this.getPersonalInfo();
    this.getHistory();
    this.getListFollow();
  }
};
</script>

<style>
.history-card {
  display: flex;
  align-items: center;
}
.histories {
  overflow: hidden;
  overflow-y: scroll;
  height: 500px;
}
.follow-item {
  padding: 5px;
  display: inline-flex;
  align-items: center;
  margin: 10px;
  width: 40%;
}
.follow-item:hover {
  background-color: #d8d8d8;
}
</style>
