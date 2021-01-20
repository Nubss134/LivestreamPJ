<template>
  <div>
    <Header />
    <el-container class="room-container">
      <SideBar />
      <el-main>
        <div class="cate-header">
          <h1 style="font-size:24px; font-weight:400">Đang phát trực tiếp</h1>
        </div>
        <ListVideo :videos="videos" />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SideBar from "@/components/SideBar";
import ListVideo from "@/components/ListVideo";
import { getAllLivestream } from "@/api/video";

export default {
  name: "Lives",
  components: {
    SideBar,
    ListVideo,
    Header
  },
  data() {
    return {
      videos: []
    };
  },
  mounted() {
    getAllLivestream().then(res => {
      this.videos = res.data;
    });
    // for (let i = 0; i < 11; i++) {
    //   this.videos.push({
    //     liveThumbnail:
    //       "https://img.nimo.tv/o/banner/BE24E87C5D4388B428E9EACB49867D_7b503af5e389189c0f6c227f243eda45.jpg/w800_l0/img.jpg",
    //     avatar:
    //       "https://img.nimo.tv/t/201906051559715943296_1599511961983_avatar.png/w360_l0/img.png",
    //     title: "Hello các bạn",
    //     streamerName: "Baroibeo"
    //   });
    // }
  }
};
</script>

<style></style>
