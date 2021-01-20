<template>
  <div style="display: inline-block;">
    <i
      class="el-icon-chat-dot-square icon-header"
      @click="dialogVisible = true"
    ></i>

    <el-dialog title="Trò chuyện" :visible.sync="dialogVisible" width="40%">
      <el-row class="chat-container">
        <el-col :span="8" class="list-friend">
          <div
            class="friend"
            v-for="(fr, i) in listFriend"
            :key="i"
            @click="selectFriend(fr)"
            :class="{ active: friendId === fr.id }"
          >
            <router-link :to="'/member/' + fr.id">
              <el-avatar :src="fr.avatar"></el-avatar>
            </router-link>
            <span style="padding-left: 10px">{{ fr.nickName }}</span>
          </div>
        </el-col>
        <el-col :span="16" class="chat-message">
          <div class="chat-send-container">
            <el-input
              placeholder="Please input"
              @keyup.enter.native="chat"
              v-model="chatContent"
            ></el-input>
          </div>
          <div class="list-message" id="msgList">
            <div v-for="(mes, i) in listMessage" :key="i">
              <div v-if="friendId !== mes.senderId" class="message-right">
                <span class="message sender">{{ mes.content }}</span>
              </div>
              <div v-else class="message-left">
                <el-avatar :src="friendAvatar"></el-avatar>
                <span class="message">{{ mes.content }}</span>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { chat } from "@/api/chat";
import { getFriends } from "@/api/user";
import firebase from "firebase/app";
import "firebase/database";

export default {
  data() {
    return {
      dialogVisible: false,
      friendId: null,
      chatContent: "",
      listFriend: [],
      listMessage: [],
      friendAvatar: "",
      refFirebase: null
    };
  },
  methods: {
    selectFriend(fr) {
      this.friendId = fr.id;
      this.friendAvatar = fr.avatar;
    },

    chat() {
      chat({
        senderId: this.$store.state.user.id,
        receiverId: this.friendId,
        content: this.chatContent
      }).then(() => {
        this.chatContent = "";
      });
    },
    getFriends() {
      getFriends(this.$store.state.user.id).then(res => {
        this.listFriend = res.data;
      });
    },
    scrollToBottom() {
      var element = document.getElementById("msgList");
      console.log(element);
      console.log("height", element.scrollHeight);
      element.scrollTop = element.scrollHeight;
    }
  },
  mounted() {
    this.getFriends();
    // firebase
    //   .database()
    //   .ref("chat/")
    //   .on("child_changed", snapshot => {
    //     console.log("a", snapshot.ref.path.toString());
    //   });
  },
  watch: {
    friendId() {
      //leave
      if (this.refFirebase) {
        this.refFirebase.off("child_added");
      }
      this.listMessage = [];
      const memberId = this.$store.state.user.id;
      if (this.friendId < memberId) {
        this.refFirebase = firebase
          .database()
          .ref("chat/" + this.friendId + "-" + memberId);
      } else {
        this.refFirebase = firebase
          .database()
          .ref("chat/" + memberId + "-" + this.friendId);
      }
      // let tmp = [];
      // this.refFirebase.once("value", snapshot => {
      //   console.log("get old message");
      //   if (snapshot.val() === null) {
      //     this.listMessage = [];
      //     return;
      //   }
      //   let data = snapshot.val();
      //   Object.keys(data).forEach(key => {
      //     tmp.push({
      //       senderId: data[key].senderId,
      //       content: data[key].content
      //     });
      //   });
      //   this.listMessage = tmp;
      // });

      this.refFirebase.on("child_added", snapshot => {
        let data = snapshot.val();
        console.log("add event", snapshot.key);
        let { senderId, content } = data;
        this.listMessage.push({
          senderId: senderId,
          content: content
        });
        this.scrollToBottom();
      });
    }
  }
};
</script>

<style>
.chat-message {
  background-color: #f8f8f8;
  height: 500px;
  border-radius: 5px;
}
.chat-container {
  height: 500px;
}
.friend {
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 10px;
}

.friend:hover {
  background-color: #f8f8f8;
}

.active {
  background-color: #f8f8f8;
}

.chat-send-container {
  position: absolute;
  bottom: 10px;
  left: 36%;
  width: 60%;
}

.list-message {
  overflow: hidden;
  height: 400px;
  padding: 20px;
}

.list-message:hover {
  overflow: hidden;
  overflow-y: scroll;
}

.list-friend {
  overflow: hidden;
  height: 500px;
}

.list-friend:hover {
  overflow: hidden;
  overflow-y: scroll;
  height: 500px;
}

.message {
  padding: 10px;
  border-radius: 20px;
  background-color: #dcdfe6;
  max-width: 200px;
}
.message-left {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}
.message-right {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 15px;
}
.sender {
  color: white;
  background-color: rgb(0, 132, 255) !important;
}
</style>
