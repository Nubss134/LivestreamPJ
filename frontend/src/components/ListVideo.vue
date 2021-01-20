<template>
  <div>
    <ul class="cate-lives">
      <li class="live-item" v-for="(v, index) in videos" :key="index">
        <el-card :body-style="{ padding: '0px' }" class="live-card">
          <router-link v-if="isReply" :to="'/video/' + v.id">
            <img :src="v.liveThumbnail" class="image-livestream" fit="fit" />
          </router-link>
          <router-link v-else :to="'/live/' + v.streamerId">
            <img :src="v.liveThumbnail" class="image-livestream" />
          </router-link>
          <div class="live-info-box">
            <router-link :to="'/member/' + v.streamerId">
              <el-avatar :src="v.avatarUrl" :size="50"></el-avatar>
            </router-link>
            <div class="live-info">
              <div class="title-livestream">{{ customRenderStr(v.title) }}</div>
              <div class="streamer-name">{{ v.nickName }}</div>
            </div>
          </div>
        </el-card>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ListVideo",
  props: ["videos", "isReply"],
  methods: {
    customRenderStr(str) {
      console.log(str.length);
      if (str.length < 30) return str;
      else {
        return str.substring(0, 27).concat("...");
      }
    }
  }
};
</script>

<style>
.streamer-name {
  margin-top: 10px;
  font-weight: bold;
  font-size: 14px;
  color: #5a5a5a;
}
.title-livestream {
  font-size: 14px;
}
.cate-lives {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 100%;
  /* text-align: center; */
}
.live-item {
  display: inline-block; /* Get all images to show in a row */
  width: 18%;
  height: 0;
  padding-bottom: 13%;
  margin: 10px;
}

.live-info-box {
  display: flex;
  align-items: center;
  padding: 10px;
}

.live-info {
  margin-left: 10px;
}
.image-livestream {
  width: 100%;
  height: auto;
}
</style>
