<template>
  <div>
    <Header />
    <Banner />
    <el-container style="background-color: #f8f8f8">
      <el-main>
        <div>
          <div class="breadcrumb">
            <span class="cate-name">
              <h2>Thể loại hàng đầu</h2>
            </span>
            <router-link to="/list-category"
              >Xem thêm<i class="el-icon-arrow-right"></i
            ></router-link>
          </div>
          <ul class="list-cate">
            <li class="cate-item" v-for="(cate, i) in topCategory" :key="i">
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
        <div v-for="(item, i) in topLivesByCategory" :key="i">
          <div class="breadcrumb">
            <span class="cate-name">
              <h2>{{ item.name }}</h2>
            </span>
            <!-- <router-link to="/category">Xem thêm<i class="el-icon-arrow-right"></i></router-link> -->
          </div>
          <ListVideo :videos="item.videos" />
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Banner from "@/components/Banner";
import ListVideo from "@/components/ListVideo";
import { getTopCateogry } from "@/api/category";
import { getTopLiveByCategory } from "@/api/video";
export default {
  name: "Home",
  components: {
    Header,
    Banner,
    ListVideo
  },
  data() {
    return {
      topCategory: null,
      topLivesByCategory: null
    };
  },
  methods: {
    getTopCategory() {
      getTopCateogry().then(res => {
        this.topCategory = res.data;
      });
    },
    getTopLiveByCategory() {
      getTopLiveByCategory().then(res => {
        this.topLivesByCategory = res.data;
      });
    }
  },
  mounted() {
    this.getTopCategory();
    this.getTopLiveByCategory();
  }
};
</script>

<style>
.breadcrumb {
  margin: 20px;
  display: flex;
  align-items: center;
}
.cate-name {
  margin-right: 20px;
}
.list-cate {
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
</style>
