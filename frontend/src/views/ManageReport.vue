<template>
  <div>
    <AdminLayout>
      <template v-slot:content>
        <div class="manage-header">
          <div>
            <h2 class="manage-title">Danh sách báo cáo</h2>
          </div>
          <div>
            <el-input
              prefix-icon="el-icon-search"
              v-model="filter"
              class="input-search"
            ></el-input>
          </div>
        </div>
        <b-table
          hover
          bordered
          :items="listReport"
          :fields="fields"
          :filter="filter"
        >
          <template #cell(action)="data">
            <el-button
              type="primary"
              plain
              @click="getReport(data.item.id)"
              style="margin-right: 10px"
              >Chi tiết</el-button
            >
            <el-popconfirm
              confirm-button-text="OK"
              cancel-button-text="No, Thanks"
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure to delete this?"
              @confirm="deleteReport(data.item.id)"
            >
              <el-button type="danger" slot="reference" plain>Xóa</el-button>
            </el-popconfirm>
          </template>
        </b-table>
        <!-- <DataTable
          title="Danh sách báo cáo"
          :columns="tableHeader"
          :rows="listReport"
          locale="es"
        >
          <th slot="thead-tr" style="width: 70px;">
            Actions
          </th>
          <template slot="tbody-tr" slot-scope="props">
            <td>
              <i
                class="el-icon-edit"
                style="color: #409EFF"
                @click="getReport(props.row.id)"
              ></i>

              <el-popconfirm
                confirm-button-text="OK"
                cancel-button-text="No, Thanks"
                icon="el-icon-info"
                icon-color="red"
                title="Are you sure to delete this?"
                @confirm="deleteReport(props.row.id)"
              >
                <i
                  class="el-icon-delete"
                  style="color: #F56C6C"
                  slot="reference"
                ></i>
              </el-popconfirm>
            </td>
          </template>
        </DataTable> -->
      </template>
    </AdminLayout>
    <el-dialog :visible.sync="dialogDetail" width="25%">
      <div style="text-align: center; margin-bottom:20px">
        <el-avatar :size="100" :src="report.avatarUrl"></el-avatar>
      </div>
      <el-form ref="report" :model="report" label-width="120px">
        <el-form-item label="Lý do">
          <el-input v-model="report.reason" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Nội dung">
          <el-input
            type="textarea"
            v-model="report.content"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="Tên streamer ">
          <el-input
            v-model="report.videoEntity.nickName"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="Thể loại">
          <el-input
            v-model="report.videoEntity.categoryName"
            :disabled="true"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <router-link
            v-if="report.videoEntity.isLive"
            :to="'/live/' + report.videoEntity.streamerId"
          >
            <el-button style="width: 100%">
              Kiểm tra
            </el-button>
          </router-link>
          <router-link v-else :to="'/video/' + report.videoEntity.id">
            <el-button style="width: 100%">
              Kiểm tra
            </el-button>
          </router-link>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import AdminLayout from "@/components/admin/AdminLayout";
import { getListReport, getReport, deleteReport } from "@/api/report";
import { Message } from "element-ui";

export default {
  components: {
    AdminLayout
  },
  data() {
    return {
      dialogDetail: false,
      report: {
        reason: "",
        content: "",
        videoEntity: {
          nickName: "",
          categoryName: ""
        }
      },
      filer: "",
      fields: [
        {
          key: "id",
          sortable: true
        },
        {
          key: "reason",
          sortable: true
        },
        {
          key: "createdTime"
        },
        {
          key: "action"
        }
      ],
      listReport: []
    };
  },
  methods: {
    getListReport() {
      getListReport().then(res => {
        this.listReport = res.data;
      });
    },
    getReport(id) {
      this.dialogDetail = true;
      getReport(id).then(res => {
        this.report = res.data;
      });
    },
    deleteReport(id) {
      deleteReport(id).then(() => {
        this.getListReport();
        Message({
          message: "Xóa thành công",
          type: "success",
          duration: 3 * 1000
        });
      });
    }
  },
  watch: {},
  mounted() {
    this.getListReport();
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
