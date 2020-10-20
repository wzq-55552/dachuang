<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import login from "./components/common/login";

export default {
  name: "App",
  components: {
    login
  }
};
</script>

<style>
@import url("./assets/css/base.css");
</style>
