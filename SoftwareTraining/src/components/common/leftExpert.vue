<template>
  <div class="home">
    <el-row class="tac">
      <!-- 导航菜单 -->
      <el-col :span="4" class="left">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          @select="handleSelect"
          router
          unique-opened
        >
          <el-submenu :index="index.toString()" v-for="(item, index) in navData" :key="index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span>{{ item.name }}</span>
            </template>
            <el-menu-item
              :index="even.path"
              v-for="(even, index) in item.evens"
              :key="index"
            >{{ even.name }}</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
      <!-- 右面内容 -->
      <el-col :span="20" style="height: 100%;overflow: auto;">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      navData: [
        {
          num: 1,
          name: "首页",
          icon: "el-icon-house",
          evens: [{ name: "我的项目", path: "/expertContent" }]
        }
      ],
      bread: [{ name: "首页", path: "/" }]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      this.bread = [];
      this.bread.push(this.navData[key]);
    },
    handleClose(key, keyPath) {
      // console.log(key, keyPath);
      this.bread = [];
    },
    handleSelect(key, keyPath) {
      this.bread.length = 1;
      let date = this.navData[keyPath[0]].evens;
      for (let key in date) {
        if (date[key].path == keyPath[1]) {
          this.bread.push(date[key]);
        }
      }
    }
  }
};
</script>

<style scoped>
.home {
  height: 100%;
}
.tac {
  height: 100%;
}
.left {
  height: 100%;
  margin-top: 80px;
  background: #ffffff;
}
.title {
  height: 75px;
  line-height: 75px;
  padding-left: 20%;
  background: #ffffff;
  font-size: 21px;
  font-family: FZZYJW--GB1-0;
  font-weight: 400;
  color: rgba(89, 187, 255, 1);
}
.admin {
  color: #7753d5;
}
.top {
  height: 57px;
  line-height: 57px;
  background: #7753d5;
  padding-left: 1px;
  text-align: right;
}
.content {
  padding-top: 23px;
  margin-top: 12px;
  margin-left: 13px;
  margin-right: 26px;
  background: #ffffff;
}
.body {
  margin-top: 12px;
  margin-left: 12px;
  margin-right: 26px;
}

.el-breadcrumb {
  height: 57px;
  line-height: 57px;
  margin-left: 12px;
}
.title-user {
  position: absolute;
  right: 15px;
  top: 0;
}
.title-user span {
  margin-left: 15px;
}
</style>
