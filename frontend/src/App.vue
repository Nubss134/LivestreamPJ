<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style>
body {
  /* Helvetica Neue,  */
  font-family: Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Arial,
    sans-serif;
  margin: 0px;
  /* background-color: #f8f8f8; */
}
a {
  text-decoration: none;
}
i {
  color: #b4b4b4;
  cursor: pointer;
}

i:hover {
  color: #409eff;
}
</style>
