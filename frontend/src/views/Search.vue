<template>
  <div>
    <Header />
    <el-container class="room-container">
      <el-main>
        <el-tabs class="result-container">
          <el-tab-pane label="Thành viên">
            <ul class="list">
              <li
                v-for="(member, i) in result.members"
                :key="i"
                class="member-item"
              >
                <router-link :to="'/member/' + member.id">
                  <el-avatar :size="130" :src="member.avatar"></el-avatar>
                </router-link>
                <div>
                  <div style="font-size:18px;">{{ member.nickName }}</div>
                  <div style="font-size:12px; color: #5A5A5A">
                    <span>{{ member.followNumber }}</span> người theo dõi
                  </div>
                </div>
              </li>
            </ul>
          </el-tab-pane>
          <el-tab-pane label="Livestream">
            <ListVideo :videos="result.videos" />
          </el-tab-pane>
          <el-tab-pane label="Thể loại">
            <div>
              <ul class="list">
                <li
                  class="cate-item"
                  v-for="(cate, i) in result.categories"
                  :key="i"
                >
                  <router-link :to="'/category/' + cate.id">
                    <el-card
                      :body-style="{ padding: '0px' }"
                      class="cate-card"
                      shadow="hover"
                    >
                      <el-image :src="cate.image" fit="fit"></el-image>
                      <p style="text-align: center;">{{ cate.name }}</p>
                    </el-card>
                  </router-link>
                </li>
              </ul>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import ListVideo from "@/components/ListVideo";
import { findByKeyWord } from "@/api/search";

export default {
  name: "Search",
  components: {
    Header,
    ListVideo
  },
  data() {
    return {
      result: {
        members: [],
        categories: [],
        videos: []
      }
    };
  },
  watch: {
    $route() {
      this.getSearchResult();
    }
  },
  methods: {
    getSearchResult() {
      findByKeyWord(this.$route.query.keyword).then(res => {
        this.result = res.data;
      });
    }
  },
  mounted() {
    this.getSearchResult();
  }
};
</script>
<style scoped>
.list {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 100%;
  text-align: center;
}
.cate-item {
  display: inline-block; /* Get all images to show in a row */
  width: 11%;
  height: 0;
  /* padding-bottom: %; */
  margin: 10px;
}

.result-container {
  margin: 0 auto;
  width: 1200px;
}

.member-item {
  display: inline-block;
  width: 18%;
  margin: 10px;
}
.live-item {
  width: 23% !important;
}
.cate-item {
  width: 15%;
}
</style>
