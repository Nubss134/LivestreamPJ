<template>
  <div>
    <Header />
    <el-container class="room-container">
      <SideBar />
      <el-main>
        <div class="user-header-container">
          <div class="user-bg">
            <el-image
              class="bg-image"
              src="https://img.nimo.tv/o/banner/1BE37A4B0ACD611959C722EA2CC93426_LeagueofLegends.jpg/l0/img.jpg"
              fit="cover"
            ></el-image>
          </div>
          <div class="user-header">
            <div class="user-header-left">
              <el-avatar
                style="margin-bottom: 15px"
                :size="sizeAvatar"
                :src="member.avatar"
              ></el-avatar>
              <div>
                <Follow
                  v-if="this.$store.state.user.isLogin"
                  :streamerId="member.id"
                ></Follow>
              </div>
            </div>
            <div class="user-header-right">
              <div class="nick-name">{{ member.nickName }}</div>
              <div class="statistic">
                <span class="follow-container">
                  <div class="statistic-title">Follow</div>
                  <div class="statistic-count">
                    {{ member.followingNumber }}
                  </div>
                </span>
                <span class="fan-container">
                  <div class="statistic-title">Followers</div>
                  <div class="statistic-count">
                    {{ member.followNumber }}
                  </div>
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="user-body">
          <div v-if="replyVideos.length === 0">
            <div class="icon-no-video"></div>
            <div class="text-no-video">
              Không có video phát lại.
            </div>
          </div>
          <div v-else class="cate-header">
            <h1 style="font-size:24px; font-weight:400">Video phát lại</h1>
            <ListVideo :videos="replyVideos" :isReply="true" />
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SideBar from "@/components/SideBar";
import ListVideo from "@/components/ListVideo";
import Follow from "@/components/Follow";
import { getPersonalInfo } from "@/api/user";
import { getReplyVideo } from "@/api/video";

export default {
  name: "Lives",
  components: {
    SideBar,
    ListVideo,
    Header,
    Follow
  },
  data() {
    return {
      sizeAvatar: 175,
      replyVideos: [],
      member: {
        id: this.$route.params.memberId,
        nickName: "",
        followNumber: 10,
        followingNumber: 10,
        avatar: null
      }
    };
  },
  methods: {
    getPersonalInfo() {
      getPersonalInfo(this.member.id).then(res => {
        this.member = res.data;
      });
    },
    getReplyVideo() {
      getReplyVideo(this.member.id).then(res => {
        this.replyVideos = res.data;
      });
    }
  },
  mounted() {
    this.getPersonalInfo();
    this.getReplyVideo();
  }
};
</script>

<style>
.fan-container {
  margin-left: 50px;
}
.text-no-video {
  margin-top: 10px;
  text-align: center;
  color: #999;
  font-size: 14px;
}
.icon-no-video {
  height: 220px;
  margin: 50px auto 0;
  background: url("../assets/icon-no-video.png") no-repeat 50%;
}
.user-bg {
  position: relative;
  margin: 0 -50px;
}
.user-header {
  margin-top: -250px;
  position: relative;
  display: flex;
  padding-top: 150px;
  padding-bottom: 30px;
}
.bg-image {
  position: absolute;
  left: 0;
  right: 0;
  height: 240px;
}
.statistic {
  display: flex;
  margin-top: 25px;
}
.user-header-left {
  text-align: center;
}
/* .user-header-right {
  align-self: flex-end;
} */
.nick-name {
  font-size: 30px;
  line-height: 36px;
  color: #fff;
  margin-top: 50px;
}
.statistic-title {
  color: #5a5a5a;
  font-size: 14px;
  margin-bottom: 5px;
}
.statistic-count {
  font-size: 26px;
}
.el-main {
  overflow: unset !important;
}
.el-image {
  display: block !important;
}
</style>
