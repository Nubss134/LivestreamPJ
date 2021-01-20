<template>
  <div>
    <Header />
    <el-container class="room-container">
      <SideBar />
      <el-main style="padding:20px 10%; background-color:#f2f2f2;">
        <el-row class="room" :gutter="20">
          <el-col class="livestream" :span="18">
            <el-card :body-style="{ padding: '0px' }" ref="player">
              <div slot="header" class="clearfix livestream-info">
                <router-link :to="'/member/' + video.streamerId">
                  <el-avatar :size="60" :src="video.avatarUrl"></el-avatar>
                </router-link>
                <div>
                  <div class="title">{{ video.title }}</div>
                  <div class="">
                    <router-link :to="'/member/' + video.streamerId">
                      <span class="name">{{ video.nickName }}</span>
                    </router-link>
                    <!-- <i class="el-icon-video-play category"> {{categoryName}}</i> -->
                    <!-- <i class="el-icon-user-solid view-number"> 2471</i> -->
                  </div>
                </div>
                <div class="toolbar">
                  <Vote
                    class="tool-item"
                    v-if="
                      isLive && isOpenVote && this.$store.state.user.isLogin
                    "
                    :voteId="video.streamerId"
                  />
                  <Follow
                    class="tool-item"
                    v-if="this.$store.state.user.isLogin && video.streamerId"
                    :streamerId="video.streamerId"
                  />
                  <el-button class="tool-item" type="primary" plain
                    >Chia sẻ</el-button
                  >
                  <i
                    class="el-icon-more-outline tool-item"
                    @click="isOpenReport = true"
                  ></i>
                </div>
              </div>
              <Player
                v-if="video.videoUrl"
                :src="video.videoUrl"
                :liveThumbnail="video.liveThumbnail"
              >
              </Player>
            </el-card>
          </el-col>
          <Comment :videoId="videoId" />
        </el-row>
      </el-main>
    </el-container>
    <el-dialog :visible.sync="isOpenReport" width="25%" title="Báo cáo">
      <el-form label-position="top" label-width="100px" :model="report">
        <el-form-item label="Lý do">
          <el-select
            v-model="report.reason"
            placeholder="Chọn lý do báo cáo"
            style="width: 100%"
          >
            <el-option value="Quảng cáo lừa đảo"></el-option>
            <el-option value="Thô tục, khiêu dâm"></el-option>
            <el-option value="Nội dung trái phép"></el-option>
            <el-option value="Xác phạm cá nhân"></el-option>
            <el-option value="Các vi phạm khác"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Nội dung">
          <el-input type="textarea" v-model="report.content"></el-input>
        </el-form-item>
        <el-button
          style="width: 100%"
          plain
          type="primary"
          @click="reportToAdmin"
          >Báo cáo</el-button
        >
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SideBar from "@/components/SideBar";
import Player from "@/components/Player";
import Comment from "@/components/Comment";
import Follow from "@/components/Follow";
import Vote from "@/components/Vote";
import { getVideoInfo } from "@/api/video";
import { report } from "@/api/report";
import { Message } from "element-ui";

export default {
  name: "Video",
  components: {
    Header,
    SideBar,
    Player,
    Comment,
    Follow,
    Vote
  },
  props: ["videoId"],
  data() {
    return {
      video: {
        id: "",
        streamerId: "",
        nickName: "",
        title: "",
        categoryName: "",
        avatarUrl: "",
        videoUrl: "",
        liveThumbnail: ""
      },
      vote: null,
      report: {
        reason: "",
        content: ""
      },
      isOpenVote: false,
      isOpenReport: false,
      isLive: this.$route.params.streamerId !== null
    };
  },
  methods: {
    getVideoInfo() {
      getVideoInfo(this.videoId).then(res => {
        let data = res.data;
        this.video.nickName = data.nickName;
        this.video.title = data.title;
        this.video.categoryName = data.categoryName;
        this.video.avatarUrl = data.avatarUrl;
        this.video.videoUrl = data.videoUrl;
        this.video.streamerId = data.streamerId;
      });
    },
    reportToAdmin() {
      this.report.videoId = this.videoId;
      report(this.report).then(() => {
        this.isOpenReport = false;
        Message({
          message: "Báo cáo thành công",
          type: "success",
          duration: 3 * 1000
        });
      });
    }
  },
  async beforeMount() {
    this.video.id = this.videoId;
    console.log("beforeMount - video", this.video.id);
    const videoInfo = await getVideoInfo(this.videoId);
    this.video.nickName = videoInfo.data.nickName;
    this.video.title = videoInfo.data.title;
    this.video.categoryName = videoInfo.data.categoryName;
    this.video.avatarUrl = videoInfo.data.avatarUrl;
    this.video.videoUrl = videoInfo.data.videoUrl;
    this.video.streamerId = videoInfo.data.streamerId;
    this.isOpenVote = videoInfo.data.isOpenVote;
  },
  mounted() {
    // let myVideo = document.getElementById("my-video");
    console.log("hegiht", this.$refs.player);
  }
};
</script>
<style>
/* .room {
    display: flex;
  } */
.name {
  font-size: 12px;
  color: #9f9f9f;
}

.room-container {
  background-image: url("../assets/bk.png");
}
.livestream-info {
  display: flex;
  align-items: center;
}
.el-avatar {
  margin-right: 10px;
}
.room {
  display: flex;
}
.box-card {
  width: 100%;
}
.el-aside {
  display: flex;
}
.title {
  font-size: 22px;
}
.other-info {
  margin-top: 10px;
}
.toolbar {
  margin-left: auto;
}
.tool-item {
  margin: 5px;
}
</style>
