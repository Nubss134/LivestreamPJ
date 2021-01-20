<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: #333">
      <el-menu
        :default-openeds="['1']"
        background-color="#222C3C"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-submenu index="1">
          <template slot="title"
            ><i class="el-icon-s-management"></i>TRANG QUẢN LÝ</template
          >
          <router-link to="/admin/manage-member">
            <el-menu-item index="1-1">Quản lý thành viên</el-menu-item>
          </router-link>
          <router-link to="/admin/manage-category">
            <el-menu-item index="1-2">Quản lý thể lọai</el-menu-item>
          </router-link>
          <router-link to="/admin/manage-report">
            <el-menu-item index="1-3">Quản lý Report</el-menu-item>
          </router-link>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 14px">
        <!-- <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>View</el-dropdown-item>
            <el-dropdown-item>Add</el-dropdown-item>
            <el-dropdown-item>Delete</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown> -->
        <router-link to="/">
          <span style="color: white">Trang chủ</span>
        </router-link>
      </el-header>
      <el-main>
        <slot name="content"></slot>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import "material-design-icons-iconfont/dist/material-design-icons.css";
export default {
  name: "AdminLayout"
};
</script>

<style>
.el-header {
  background-color: #3b4350;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
table {
  font-size: 14px !important;
}
table {
  text-align: center;
}

.manage-header {
  display: flex;
  align-items: center;
  /* align-content: space-between; */
  justify-content: space-between;
}
</style>
