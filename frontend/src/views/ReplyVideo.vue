<template>
  <div>
    <Video :videoId="videoId" />
  </div>
</template>

<script>
import Video from "@/components/Video";
export default {
  components: {
    Video
  },
  data() {
    return {
      videoId: this.$route.params.videoId
    };
  }
};
</script>

<style></style>
