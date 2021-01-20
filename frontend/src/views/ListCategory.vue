<template>
  <div>
    <Header />
    <el-container class="room-container">
      <SideBar />
      <el-main>
        <div class="cate-header">
          <h1 style="font-size: 28px; font-weight: 400">Tất cả thể loại</h1>
        </div>
        <div>
          <ul class="list-cate">
            <li class="cate-item" v-for="(cate, i) in listCateogry" :key="i">
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
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SideBar from "@/components/SideBar";
import { getAll } from "@/api/category";
export default {
  name: "ListCategory",
  components: {
    Header,
    SideBar
  },
  data() {
    return {
      listCateogry: null
    };
  },
  methods: {
    getAllCategory() {
      getAll().then(res => {
        this.listCateogry = res.data;
      });
    }
  },
  mounted() {
    this.getAllCategory();
  }
};
</script>

<style>
.list-cate {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 100%;
}
.cate-item {
  display: inline-block; /* Get all images to show in a row */
  width: 11%;
  height: 0;
  /* padding-bottom: %; */
  margin: 10px;
}
</style>
