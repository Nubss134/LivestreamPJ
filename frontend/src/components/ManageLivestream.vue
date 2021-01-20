<template>
  <div>
    <el-dialog :visible.sync="show" width="25%" :before-close="handleClose">
      <div>
        <el-tabs v-model="activeName">
          <el-tab-pane label="Thông tin" name="first">
            <el-row>
              <el-col :span="24">
                <el-form
                  status-icon
                  label-position="top"
                  label-width="100px"
                  :model="stream"
                  :rules="rulesSetting"
                  ref="stream"
                >
                  <el-form-item label="Tiêu đề" prop="title">
                    <el-input v-model="stream.title"></el-input>
                  </el-form-item>
                  <el-form-item label="Thể loại" prop="categoryId">
                    <el-select
                      v-model="stream.categoryId"
                      style="width: 100%"
                      placeholder="Chọn thể loại"
                    >
                      <el-option
                        v-for="(cate, i) in listCategory"
                        :key="i"
                        :label="cate.name"
                        :value="cate.id"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="Live thumbnail" prop="liveThumbnail">
                    <Upload
                      @uploadSuccess="stream.liveThumbnail = $event"
                      :oldImage="stream.liveThumbnail"
                    >
                    </Upload>
                  </el-form-item>
                  <el-button
                    type="primary"
                    @click="createStreamer"
                    plain
                    style="width: 100%"
                    >Lưu</el-button
                  >
                </el-form>
              </el-col>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="Stream URL" name="second">
            <el-form label-position="top" label-width="100px" :model="stream">
              <el-form-item label="Server URL">
                <el-input v-model="serverUrl"></el-input>
              </el-form-item>
              <el-form-item label="Stream key">
                <el-input v-model="streamKey"></el-input>
              </el-form-item>
              <div>
                <p style="font-style: italic;">
                  Coppy URL và Stream Key vào phần mềm OBS hoặc các phần mềm
                  tương đương khác để livestream.
                  <span
                    ><router-link
                      :to="'/live/' + this.$store.state.user.id"
                      target="_blank"
                    >
                      Đi tới livestream
                    </router-link>
                  </span>
                </p>
              </div>

              <el-button
                type="primary"
                @click="generateStreamKey"
                plain
                style="width: 100%"
                >Tạo key mới</el-button
              >
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="Bình chọn" name="third">
            <div v-if="isOpenVote">
              <el-form label-position="top" label-width="100px">
                <el-form-item :label="vote.title"> </el-form-item>
                <el-form-item :label="'1: ' + vote.choice1">
                  <el-progress
                    :text-inside="false"
                    :stroke-width="26"
                    :percentage="70"
                  ></el-progress>
                </el-form-item>
                <el-form-item :label="'2: ' + vote.choice2">
                  <el-progress
                    :text-inside="false"
                    :stroke-width="26"
                    :percentage="30"
                  ></el-progress>
                </el-form-item>
                <el-form-item :label="'3: ' + vote.choice3">
                  <el-progress
                    :text-inside="false"
                    :stroke-width="26"
                    :percentage="0"
                  ></el-progress>
                </el-form-item>
                <el-button type="primary" plain @click="closeVote"
                  >Đóng bình chọn</el-button
                >
              </el-form>
            </div>
            <div v-else>
              <el-form
                label-position="top"
                label-width="100px"
                :model="vote"
                ref="vote"
                :rules="rulesVote"
                status-icon
              >
                <el-form-item label="Tiêu đề" prop="title">
                  <el-input v-model="vote.title"></el-input>
                </el-form-item>
                <el-form-item label="Lựa chọn 1" prop="choice1">
                  <el-input v-model="vote.choice1"></el-input>
                </el-form-item>
                <el-form-item label="Lựa chọn 2" prop="choice2">
                  <el-input v-model="vote.choice2"></el-input>
                </el-form-item>
                <el-form-item label="Lựa chọn 3" prop="choice3">
                  <el-input v-model="vote.choice3"></el-input>
                </el-form-item>
                <el-button
                  type="primary"
                  @click="openVote"
                  style="width:100%"
                  plain
                  >Tạo bình chọn</el-button
                >
              </el-form>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getAll } from "@/api/category";
import { create, getInfo, generateStreamKey } from "@/api/streamer";
import { Message } from "element-ui";
import { findById, openVote, closeVote } from "@/api/vote";
import Upload from "@/components/Upload";

export default {
  name: "ManageLivestream",
  components: {
    Upload
  },
  props: ["dialogVisible"],
  data() {
    return {
      isOpenVote: false,
      stream: {
        title: "",
        categoryId: "",
        liveThumbnail: ""
      },
      rulesSetting: {
        title: [
          {
            required: true,
            message: "Vui lòng nhập tiêu đề",
            trigger: "blur"
          }
        ],
        categoryId: [
          {
            required: true,
            message: "Vui lòng chọn thể  loại",
            trigger: "blur"
          }
        ],
        liveThumbnail: [
          {
            required: true,
            message: "Vui lòng chọn ảnh",
            trigger: "blur"
          }
        ]
      },
      vote: {
        title: "",
        choice1: "",
        choice2: "",
        choice3: ""
      },
      rulesVote: {
        title: [
          {
            required: true,
            message: "Vui lòng nhập tiêu đề",
            trigger: "blur"
          }
        ],
        choice1: [
          {
            required: true,
            message: "Vui lòng nhập lựa chọn 1",
            trigger: "blur"
          }
        ],
        choice2: [
          {
            required: true,
            message: "Vui lòng nhập lựa chọn 2",
            trigger: "blur"
          }
        ],
        choice3: [
          {
            required: true,
            message: "Vui lòng nhập lựa chọn 3",
            trigger: "blur"
          }
        ]
      },
      streamKey: null,
      serverUrl: "rtmp://localhost:1935/live",
      activeName: "first",
      listCategory: []
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
    getLivestreamInfo() {
      getInfo().then(res => {
        this.streamKey = res.data.streamKey;
        this.stream = res.data;
      });
    },
    getListCategory() {
      getAll().then(res => {
        this.listCategory = res.data;
      });
    },
    createStreamer() {
      this.$refs.stream.validate(valid => {
        if (!valid) {
          return;
        }
        create(this.stream).then(() => {
          Message({
            message: "Lưu thành công",
            type: "success",
            duration: 3 * 1000
          });
        });
      });
    },
    generateStreamKey() {
      generateStreamKey().then(res => {
        this.streamKey = res.data;
      });
    },
    openVote() {
      this.$refs.vote.validate(valid => {
        if (!valid) {
          return;
        }
        openVote(this.vote).then(() => {
          Message({
            message: "Tạo thành công",
            type: "success",
            duration: 3 * 1000
          });
          this.isOpenVote = true;
        });
      });
    },
    closeVote() {
      closeVote().then(() => {
        Message({
          message: "Đóng thành công",
          type: "success",
          duration: 3 * 1000
        });
        this.isOpenVote = false;
      });
    },
    getVote() {
      findById(this.$store.state.user.id).then(res => {
        if (res.data === "") {
          this.isOpenVote = false;
        } else {
          this.isOpenVote = true;
          this.vote.title = res.data.title;
          this.vote.choice1 = res.data.choice1;
          this.vote.choice2 = res.data.choice2;
          this.vote.choice3 = res.data.choice3;
        }
      });
    }
  },
  mounted() {
    this.getListCategory();
    this.getLivestreamInfo();
    this.getVote();
  }
};
</script>

<style>
.history-card {
  display: flex;
  align-items: center;
}
</style>
