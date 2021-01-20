<template>
  <div class="video">
    <video ref="videoPlayer" class="video-js"></video>
  </div>
</template>

<script>
import videojs from "video.js";
import "video.js/dist/video-js.css";
// require("videojs-contrib-quality-levels");
// require("videojs-hls-quality-selector");

export default {
  name: "Player",
  props: ["src", "liveThumbnail"],
  data() {
    return {
      player: null
    };
  },
  mounted() {
    this.player = videojs(this.$refs.videoPlayer, {
      autoplay: false,
      controls: true,
      fluid: true,
      // poster: this.liveThumbnail,
      sources: [
        {
          src: this.src,
          type: "application/x-mpegURL"
        }
      ]
    });
    let height = this.$refs.videoPlayer.offsetHeight - 20 + "px";
    console.log("xx", height);
    console.log("hegiht1", this.$refs.videoPlayer.offsetHeight);
    console.log("conte", document.getElementsByClassName("chat-content"));
    document.getElementById("chat-id").style.height = height;

    // this.player.hlsQualitySelector({
    //   displayCurrentQuality: true
    // });
  }
};
</script>

<style></style>
