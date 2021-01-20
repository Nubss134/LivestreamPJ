<template>
  <el-upload
    class="avatar-uploader"
    action="https://api.imgur.com/3/image"
    :headers="headers"
    name="image"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <img v-else-if="oldImage" :src="oldImage" class="avatar" />
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>
<script>
export default {
  name: "Upload",
  props: ["oldImage"],
  data() {
    return {
      imageUrl: "",
      headers: { Authorization: "Client-ID 15ccb375483222a" }
    };
  },
  methods: {
    handleAvatarSuccess(res) {
      this.imageUrl = res.data.link;
      this.$emit("uploadSuccess", this.imageUrl);
    }
  }
};
</script>

<style>
/* .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
} */
/* .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
} */
.avatar {
  width: 100%;
  height: 100%;
  display: block;
}
</style>
