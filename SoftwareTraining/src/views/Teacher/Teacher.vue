<template>
  <div>
    <Header :isShow="false"></Header>
    <Left></Left>
  </div>
</template>

<script>
import Header from "../../components/common/header";
import Left from "../../components/common/leftTeacher";

export default {
  name: "Teacher",
  components: {
    Header,
    Left
  },
  methods: {}
};
</script>
<style>
</style>
