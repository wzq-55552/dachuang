<template>
  <div>
    <Header></Header>
    <Left></Left>
    <!-- <router-view></router-view> -->
  </div>
</template>

<script>
import Header from "../../components/common/header";
import Left from "../../components/common/left";
export default {
  name: "index_stu",
  components: {
    Header,
    Left
  }
};
</script>

<style>
</style>
