<template>
  <div>
    <AdminLayout>
      <template v-slot:content>
        <div class="manage-header">
          <div>
            <h2 class="manage-title">Danh sách thể loại</h2>
          </div>
          <div>
            <el-button
              type="primary"
              plain
              @click="dialogAdd = true"
              style="margin-right: 10px"
              >Thêm mới</el-button
            >
            <el-input
              prefix-icon="el-icon-search"
              v-model="filter"
              style="width: auto"
            ></el-input>
          </div>
        </div>
        <div>
          <el-dialog :visible.sync="dialogAdd" width="30%" @close="resetForm">
            <div>
              <el-form
                label-position="top"
                label-width="100px"
                :model="category"
                ref="category"
                :rules="rules"
                status-icon
              >
                <el-form-item label="Tên thể loại" prop="name">
                  <el-input v-model="category.name"></el-input>
                </el-form-item>
                <el-form-item label="Chi tiết" prop="description">
                  <el-input
                    type="textarea"
                    v-model="category.description"
                  ></el-input>
                </el-form-item>
                <el-form-item label="Ảnh hiển thị" prop="image">
                  <Upload
                    v-if="dialogAdd"
                    @uploadSuccess="category.image = $event"
                    :oldImage="category.image"
                  ></Upload>
                </el-form-item>
                <el-form-item label="Ảnh bìa" prop="imageBanner">
                  <Upload
                    v-if="dialogAdd"
                    @uploadSuccess="category.imageBanner = $event"
                    :oldImage="category.imageBanner"
                  ></Upload>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    plain
                    style="width:100%"
                    @click="save"
                  >
                    Lưu
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-dialog>
        </div>
        <!-- <DataTable
          title="Danh sách thể loại"
          :columns="tableHeader"
          :rows="listCategory"
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
                @click="detail(props.row)"
              ></i>

              <el-popconfirm
                confirm-button-text="OK"
                cancel-button-text="No, Thanks"
                icon="el-icon-info"
                icon-color="red"
                title="Are you sure to delete this?"
                @confirm="deleteCategory(props.row.id)"
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
        <b-table
          hover
          bordered
          :items="listCategory"
          :fields="fields"
          :filter="filter"
        >
          <template #cell(action)="data">
            <el-button
              type="primary"
              plain
              @click="detail(data.item)"
              style="margin-right: 10px"
              >Chi tiết</el-button
            >
            <el-popconfirm
              confirm-button-text="OK"
              cancel-button-text="No, Thanks"
              icon="el-icon-info"
              icon-color="red"
              title="Are you sure to delete this?"
              @confirm="deleteCategory(data.item.id)"
            >
              <el-button type="danger" slot="reference" plain>Xóa</el-button>
            </el-popconfirm>
          </template>
        </b-table>
      </template>
    </AdminLayout>
    <!-- <el-dialog :visible.sync="dialogDetail" width="25%">
      <div style="text-align: center; margin-bottom:20px">
        <el-avatar
          :size="100"
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
      </div>
      <el-form ref="member" :model="member" label-width="120px">
        <el-form-item label="Tên đăng nhập">
          <el-input v-model="member.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Tên hiển thị">
          <el-input v-model="member.nickName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Đang theo dõi">
          <el-input v-model="member.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Theo dõi">
          <el-input v-model="member.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="Ngày tạo">
          <el-date-picker
            type="date"
            placeholder="Pick a date"
            v-model="member.updatedTime"
            style="width: 100%;"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="Cập nhật cuối">
          <el-date-picker
            type="date"
            placeholder="Pick a date"
            v-model="member.updatedTime"
            style="width: 100%;"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="Vô hiệu hóa">
          <el-switch v-model="member.isBan"></el-switch>
        </el-form-item>
      </el-form>
    </el-dialog> -->
  </div>
</template>

<script>
import AdminLayout from "@/components/admin/AdminLayout";
import Upload from "@/components/Upload";
import { Message } from "element-ui";
import { getAll, deleteCategory, save } from "@/api/category";

export default {
  components: {
    AdminLayout,
    Upload
  },
  data() {
    return {
      id: null,
      dialogAdd: false,
      dialogDetail: false,
      category: {
        name: "",
        description: "",
        image: "",
        imageBanner: ""
      },
      rules: {
        name: [
          {
            required: true,
            message: "Vui lòng nhập tên",
            trigger: "blur"
          }
        ],
        description: [
          {
            required: true,
            message: "Vui lòng nhập mô tả",
            trigger: "blur"
          }
        ],
        image: [
          {
            required: true,
            message: "Vui lòng chọn ảnh hiển thị",
            trigger: "blur"
          }
        ],
        imageBanner: [
          {
            required: true,
            message: "Vui lòng chọn ảnh bìa",
            trigger: "blur"
          }
        ]
      },
      filter: "",
      fields: [
        {
          key: "id",
          sortable: true
        },
        {
          key: "name",
          sortable: true
        },
        {
          key: "createdTime",
          sortable: true
        },
        {
          key: "updatedTime",
          sortable: true
        },
        {
          key: "action"
        }
      ],
      listCategory: []
    };
  },
  methods: {
    getAll() {
      getAll().then(res => {
        this.listCategory = res.data;
      });
    },
    deleteCategory(id) {
      deleteCategory(id).then(() => {
        Message({
          message: "Xóa thành công",
          type: "success",
          duration: 3 * 1000
        });
        this.getAll();
      });
    },
    save() {
      this.$refs.category.validate(valid => {
        if (!valid) {
          return;
        }
        save(this.category).then(() => {
          Message({
            message: "Lưu thành công",
            type: "success",
            duration: 3 * 1000
          });
          this.getAll();
          this.$router.go();
        });
      });
    },
    detail(data) {
      this.category = data;
      this.dialogAdd = true;
    },
    resetForm() {
      this.category = {
        name: "",
        description: "",
        image: "",
        imageBanner: ""
      };
    }
  },
  watch: {},
  mounted() {
    this.getAll();
  }
};
</script>

<style></style>
