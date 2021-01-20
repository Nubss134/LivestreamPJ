<template>
  <Video v-if="videoId" :videoId="videoId" />
</template>

<script>
import Video from "@/components/Video";
import { getLivestreamId } from "@/api/video";
import { saveHistory } from "@/api/history";
export default {
  components: {
    Video
  },
  data() {
    return {
      videoId: null
    };
  },
  async beforeMount() {
    const res = await getLivestreamId(this.$route.params.streamerId);
    if (res.data !== null) {
      this.videoId = res.data;
      saveHistory(this.videoId);
    }
  }
};
</script>

<style></style>
