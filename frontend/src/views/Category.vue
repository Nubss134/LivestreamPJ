<template>
  <div>
    <Header />
    <el-container class="room-container">
      <SideBar />
      <div class="cate-banner">
        <!-- <el-image
        src="https://img.nimo.tv/o/banner/BAAE856C9C2D3EBEFCB4120D0E40F9B4_f6fe0c579e2c67825aefcfe1f66cb637.jpg/l0/img.jpg"
        :fit="fill">
        </el-image> -->
        <div class="img-banner">
          <img
            style="width: 100%;height: auto;"
            :src="info.imageBanner"
            alt=""
            fit="cover"
          />
        </div>
        <div class="cate-info">
          <div class="cate-name">
            <h1 style="font-size: 46px;">{{ info.name }}</h1>
          </div>
          <div class="cate-des">
            {{ info.description }}
          </div>
        </div>
        <el-main>
          <h2>Danh sách livestream</h2>
          <ListVideo :videos="videos" />
        </el-main>
      </div>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SideBar from "@/components/SideBar";
import ListVideo from "@/components/ListVideo";
import { getLivestreamByCategory } from "@/api/video";
import { findById } from "@/api/category";
export default {
  name: "Category",
  components: {
    SideBar,
    ListVideo,
    Header
  },
  data() {
    return {
      videos: [],
      info: {
        name: "",
        description: "",
        imageBanner: ""
      }
    };
  },
  mounted() {
    getLivestreamByCategory(this.$route.params.categoryId).then(res => {
      this.videos = res.data;
    });
    findById(this.$route.params.categoryId).then(res => {
      this.info = res.data;
    });
  }
};
</script>

<style>
/* .cate-banner {
  width: 100%;
  position: relative;
  height: 100px;
}
.cate-description {
  position: absolute;
  top: 50%;
  left: 10%;
} */
.cate-info {
  position: absolute;
  top: 100px;
  left: 80px;
  color: white;
}
.cate-des {
  max-width: 600px;
  font-size: 14px;
}
</style>
