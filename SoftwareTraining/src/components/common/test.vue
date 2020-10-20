<template>
  <div class="home">
    <el-row class="tac">
      <!-- 导航菜单 -->
      <el-col :span="4" class="left">
        <el-row>
          <el-col :span="24"><div class="grid-content bg-purple-dark title"><span class="backstage">后台</span><span class="admin">管理系统</span></div></el-col>
        </el-row>
         <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" @select="handleSelect" router unique-opened>
          <!-- <el-menu-item index="/">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
          </el-menu-item> -->
          <el-submenu :index="index.toString()" v-for="(item, index) in navData" :key="index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span>{{ item.name }}</span>
            </template>
              <el-menu-item :index="even.path" v-for="(even, index) in item.evens" :key="index">{{ even.name }}</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
      <!-- 右面内容 -->
      <el-col :span='20'>
        <el-row>
          <el-col :span="24">
            <div class="grid-content bg-purple-dark top">
              <el-breadcrumb separator=">">
                <el-breadcrumb-item v-for="(item, index) in bread" :to=item.path :key='index'>{{item.name}}</el-breadcrumb-item>
              </el-breadcrumb>
              <span class="title-user">
                <i class="el-icon-circle-close"></i>
                <span>头像</span>
                <span>一级管理员</span>
              </span>
            </div>
          </el-col>
          <el-col :span="24"><div class="body"><router-view></router-view></div></el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
 
<script>
 export default {
    name: 'home',
    data() {
      return {
        navData: [ 
          { num: 1 , name: "首页", icon: 'el-icon-house' , evens: [ { name:'首页', path: '/' } ] },
          { num: 2 , name: "用户管理", icon: 'el-icon-s-custom' , evens: [ { name:'用户数据', path: 'UserData' }, 
            { name: '用户反馈', path: 'UserFeedback' }, { name: '异常用户', path: 'UserabNormal' }] 
          },
          { num: 3 , name: "广告管理", icon: 'el-icon-s-data' , evens: [ { name:'广告数据', path: 'AdvertisingData' }, 
            { name: '广告项目', path: 'AdvertisingProject' }, { name: '添加广告', path: 'AdvertisingAdd' } ] 
          },
          { num: 4 , name: "审核管理", icon: 'el-icon-folder-checked' , evens: [ { name:'任务审核', path: 'ExpertAudit' }, 
            { name: '提现审核', path: 'TaskAudit' }, { name: '达人审核', path: 'WithdrawalAudit' } ] 
            },
          { num: 5 , name: "活动管理", icon: 'el-icon-present' , evens: [ { name:'签到设置', path: 'Signset' }, { name: '悬赏收徒', path: 'Recruitset' }, 
            { name: '提现设置', path: 'WithdrawDepositSet' }, { name: '公告设置', path: 'Noticeset' }, { name: '活动设置', path: 'Activityset' } ] 
            },
          { num: 6 , name: "系统设置", icon: 'el-icon-folder-checked' , evens: [ { name:'修改密码', path: 'ModificationPassword' } ] },
           ],
        bread : [
          { name: '首页', path: '/' }
        ],
 
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        this.bread = [];
        this.bread.push(this.navData[key])
      },
      handleClose(key, keyPath) {
        // console.log(key, keyPath);
        this.bread = []
      },
      handleSelect(key, keyPath) {
        this.bread.length = 1;
        let date = this.navData[keyPath[0]].evens
        for (let key in date) {
          if(date[key].path == keyPath[1]){
            this.bread.push(date[key])
          }
        }
      }
    }
  }
</script>
 
<style scoped>
  .home {
    height: 100%;
  }
  .tac {
    height: 100%;
  }
  .left {
    height:100%;
    background: #ffffff;
  }
  .title {
    height: 75px;
    line-height: 75px;
    padding-left: 20%;
    background: #ffffff;
    font-size:21px;
    font-family:FZZYJW--GB1-0;
    font-weight:400;
    color:rgba(89,187,255,1);
  }
  .admin {
    color:#7753D5;
  }
  .top {
    height:57px;
    line-height:57px;
    background:#7753D5;
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
