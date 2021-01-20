<template>
  <div>
    <AdminLayout>
      <template v-slot:content>
        <div class="manage-header">
          <div>
            <h2 class="manage-title">Danh sách thành viên</h2>
          </div>
          <div>
            <el-input
              prefix-icon="el-icon-search"
              v-model="filter"
              class="input-search"
            ></el-input>
          </div>
        </div>
        <div>
          <b-table
            hover
            bordered
            :items="listMember"
            :fields="fields"
            :filter="filter"
          >
            <template #cell(isBan)="data">
              <el-switch
                :value="data.value"
                active-color="#ff4949"
                inactive-color="#13ce66"
                @change="changeStatus(data.item.id)"
              >
              </el-switch>
            </template>
            <template #cell(action)="data">
              <el-button type="primary" plain @click="detailMember(data.item)"
                >Chi tiết</el-button
              >
            </template>
          </b-table>
        </div>
      </template>
    </AdminLayout>
    <el-dialog :visible.sync="dialogDetail" width="25%">
      <div style="text-align: center; margin-bottom:20px">
        <el-avatar :size="100" :src="member.avatar"></el-avatar>
      </div>
      <el-form ref="member" :model="member" label-width="120px">
        <el-form-item label="Tên đăng nhập">
          <el-input v-model="member.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Tên hiển thị">
          <el-input v-model="member.nickName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Vai trò">
          <el-input v-model="member.role" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Đang theo dõi">
          <el-input
            v-model="member.followingNumber"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="Theo dõi">
          <el-input v-model="member.followNumber" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Ngày tạo">
          <el-input v-model="member.createdTime" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import AdminLayout from "@/components/admin/AdminLayout";
import {
  getAllMember,
  deleteMember,
  changeStatus,
  getPersonalInfo
} from "@/api/user";
import { Message } from "element-ui";

export default {
  components: {
    AdminLayout
  },
  data() {
    return {
      dialogDetail: false,
      filter: "",
      member: {
        username: "",
        nickName: "",
        createdTime: null,
        role: null,
        avatar: null
      },
      listMember: [],
      fields: [
        {
          key: "id",
          sortable: true
        },
        {
          key: "username",
          sortable: true
        },
        {
          key: "nickName",
          sortable: true
        },
        {
          key: "phone",
          sortalble: true
        },
        {
          key: "email",
          sortable: true
        },
        {
          key: "createdTime"
        },
        "isBan",
        {
          key: "action"
        }
      ]
    };
  },
  methods: {
    detailMember(item) {
      this.dialogDetail = true;
      console.log(item);
      getPersonalInfo(item.id).then(res => {
        this.member = res.data;
      });
    },
    getAllMember() {
      getAllMember().then(res => {
        this.listMember = res.data;
      });
    },
    deleteMember(memberId) {
      deleteMember(memberId).then(() => {
        Message({
          message: "Xóa thành công",
          type: "success",
          duration: 3 * 1000
        });
        this.getAllMember();
      });
    },
    changeStatus(memberId) {
      console.log("change status");
      console.log(memberId);
      changeStatus(memberId).then(() => {
        this.getAllMember();
        Message({
          message: "Thành công",
          type: "success",
          duration: 3 * 1000
        });
      });
    }
  },
  watch: {},
  mounted() {
    this.getAllMember();
  }
};
</script>

<style>
table {
  width: 100%;
  font-size: 18px;
}
i {
  font-size: 20px;
  margin-right: 10px;
}
</style>
