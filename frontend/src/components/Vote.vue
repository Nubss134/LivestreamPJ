<template>
  <span>
    <el-button @click="handleClick" type="primary" plain
      >Cuộc bình chọn</el-button
    >

    <el-dialog :visible.sync="dialogVisible" width="30%">
      <div>
        <div v-if="!isVote">
          <el-form label-position="top" label-width="100px">
            <el-form-item>
              <el-radio-group v-model="choice">
                <div>
                  <el-radio label="1" border>{{ vote.choice1 }}</el-radio>
                </div>
                <div style="margin-top:20px">
                  <el-radio label="2" border>{{ vote.choice2 }}</el-radio>
                </div>
                <div style="margin-top:20px">
                  <el-radio label="3" border>{{ vote.choice3 }}</el-radio>
                </div>
              </el-radio-group>
            </el-form-item>
            <el-button type="primary" plain @click="votting"
              >Bầu chọn</el-button
            >
          </el-form>
        </div>
        <div v-else>
          <el-form label-position="top" label-width="100px">
            <el-form-item :label="vote.title"> </el-form-item>
            <el-form-item :label="'1: ' + vote.choice1">
              <el-progress
                :text-inside="true"
                :stroke-width="26"
                :percentage="result.choice1"
              ></el-progress>
            </el-form-item>
            <el-form-item :label="'2: ' + vote.choice2">
              <el-progress
                :text-inside="true"
                :stroke-width="26"
                :percentage="result.choice2"
              ></el-progress>
            </el-form-item>
            <el-form-item :label="'3: ' + vote.choice3">
              <el-progress
                :text-inside="true"
                :stroke-width="26"
                :percentage="result.choice3"
              ></el-progress>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" plain @click="getVoteResult"
                >Cập nhật</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </div>
    </el-dialog>
  </span>
</template>

<script>
import { vote, getVoteResult, isVote, findById } from "@/api/vote";
import { Message } from "element-ui";

export default {
  props: ["voteId"],
  data() {
    return {
      vote: {
        title: "",
        choice1: "",
        choice2: "",
        choice3: ""
      },
      dialogVisible: false,
      choice: null,
      isVote: null,
      result: {
        choice1: 0,
        choice2: 0,
        choice3: 0
      }
    };
  },
  methods: {
    handleClick() {
      this.dialogVisible = true;
      this.getVoteInfo();
      this.getVoteResult();
    },
    votting() {
      vote({
        choice: this.choice,
        voteId: this.vote.id
      }).then(() => {
        this.isVote = true;
        this.getVoteResult();
        Message({
          message: "Bình chọn thành công",
          type: "success",
          duration: 3 * 1000
        });
      });
    },
    getVoteResult() {
      getVoteResult(this.voteId).then(res => {
        this.result = res.data;
      });
    },
    getVoteInfo() {
      if (this.voteId) {
        findById(this.$route.params.streamerId).then(res => {
          this.vote = res.data;
        });
      }
    }
  },
  mounted() {
    isVote(this.voteId).then(res => {
      this.isVote = res.data;
    });
  }
};
</script>

<style></style>
