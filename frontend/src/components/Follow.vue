<template>
  <span>
    <el-button type="primary" plain v-if="isFollow" @click="unFollow"
      >Hủy theo dõi</el-button
    >
    <el-button type="primary" plain v-else @click="follow">Theo dõi</el-button>
  </span>
</template>

<script>
import { isFollow, follow, unFollow } from "@/api/follow.js";
export default {
  props: ["streamerId"],
  name: "Follow",
  data() {
    return {
      isFollow: null
    };
  },
  methods: {
    follow() {
      follow(this.streamerId).then(() => {
        this.isFollow = true;
      });
    },
    unFollow() {
      unFollow(this.streamerId).then(() => {
        this.isFollow = false;
      });
    }
  },
  mounted() {
    isFollow(this.streamerId).then(res => {
      this.isFollow = res.data;
    });
  }
};
</script>

<style></style>
