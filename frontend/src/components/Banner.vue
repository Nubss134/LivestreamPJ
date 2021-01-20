<template>
  <div class="banner">
    <!-- <el-carousel :interval="5000" arrow="always" height="55vh">
      <el-carousel-item v-for="(banner, i) in listBanner" :key="i">
        <player :src="banner.videoUrl" :liveThumbnail="banner.liveThumbnail">
        </player>
      </el-carousel-item>
    </el-carousel> -->
    <!-- <div class="video-banner"> -->
    <!-- <player
        src="http://localhost:8080/live/hello.m3u8"
        liveThumbnail="banner.liveThumbnail"
      >
      </player> -->
    <!-- </div> -->
    <!-- <el-carousel :interval="4000" width="900px">
      <el-carousel-item v-for="item in 6" :key="item">
        <el-image
          style="width: 900px"
          src="https://img.nimo.tv/o/banner/C927A852D23EB982453BC92EE2F662B2_73beb04ada3a79098aee955efc822169.jpg/l0/img.webp"
          fit="fit"
        ></el-image>
      </el-carousel-item>
    </el-carousel> -->
  </div>
</template>

<script>
// import Player from "@/components/Player";
import { getBanner } from "@/api/video";
export default {
  name: "Banner",
  components: {
    // Player
  },
  data() {
    return {
      listBanner: []
    };
  },
  methods: {
    getBanner() {
      getBanner().then(res => {
        this.listBanner = res.data;
      });
    }
  },
  mounted() {
    this.getBanner();
  }
};
</script>

<style>
.banner {
  height: 60vh;
  width: 100vw;
  background-image: url("../assets/banner.png");
  background-size: 100% 100%;
  display: flex;
  align-items: center;
}
.el-carousel {
  width: 50%;
  left: 25%;
}

.video-banner {
  margin-left: 25%;
  height: 55vh;
  width: 50%;
}
</style>
