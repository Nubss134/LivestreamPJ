<template>
  <el-col class="chat" :span="6">
    <el-card class="box-card" body-style="padding: 5px">
      <div slot="header" class="clearfix" style="text-align:center;">
        <span><h2>Bình luận</h2></span>
      </div>
      <ul class="chat-content" id="chat-id">
        <div v-for="(cmt, i) in comments" :key="i" class="text item">
          <div class="cmt-item">
            <router-link :to="'/member/' + cmt.memberId">
              <span style="color: rgb(138, 142, 202)"
                >{{ cmt.username }}:
              </span>
            </router-link>
            <span style="color: #5A5A5A"> {{ cmt.content }}</span>
          </div>
        </div>
      </ul>
      <div class="chat-input">
        <el-input
          placeholder="Talk to me!"
          v-model="commentContent"
          @keyup.enter.native="comment"
        >
          <el-button
            slot="append"
            icon="el-icon-s-promotion"
            @click="comment"
          ></el-button>
        </el-input>
      </div>
    </el-card>
  </el-col>
</template>

<script>
import firebase from "firebase/app";
import "firebase/database";
import { pushComment } from "@/api/comment";

export default {
  name: "Comment",
  props: ["videoId"],
  data() {
    return {
      commentContent: "",
      comments: [],
      refFirebase: firebase.database().ref("comment/" + this.videoId)
    };
  },
  methods: {
    comment() {
      let dataComment = {
        content: this.commentContent
      };
      pushComment(this.videoId, dataComment)
        .then(() => {
          this.commentContent = "";
        })
        .catch(e => {
          console.log(e);
        });
    },
    showComment() {
      let commentsTmp = [];
      this.refFirebase.once("value", snapshot => {
        console.log("show old cmt");
        if (snapshot.val() === null) return;
        let data = snapshot.val();
        Object.keys(data).forEach(key => {
          commentsTmp.push({
            memberId: data[key].memberId,
            username: data[key].username,
            content: data[key].content
          });
        });
        this.comments = commentsTmp;
      });
    }
  },
  mounted() {
    this.showComment();
    this.refFirebase
      .endAt()
      .limitToLast(1)
      .on("child_added", snapshot => {
        console.log("event add");
        let data = snapshot.val();
        let { username, content, memberId } = data;
        this.comments.push({
          memberId: memberId,
          username: username,
          content: content
        });
      });
  }
};
</script>

<style>
.chat {
  display: flex;
}
/* .chat-input {
  position: absolute;
  bottom: 10px;
  width: 18%;
} */

/* ul{height:200px; width:18%;} */
/* .chat-content {
  padding-left: 15px;
  height: 100%;
} */

.chat-content {
  padding-left: 15px;
  height: 700px;
  /* overflow: hidden; */
  overflow-y: scroll;
}

.cmt-item {
  padding: 5px;
  margin: 2px;
}
</style>
