<template>
  <div id="eTb" v-show="isShow">
    <el-table :data="expertData" stripe style="width: 100%" v-show="isShow" class="e-table">
      <el-table-column prop="collegeId" label="所属学院" width="180" :formatter="GetCollegeName"></el-table-column>
      <el-table-column prop="userName" label="姓名" width="180"></el-table-column>
      <el-table-column prop="userId" label="专家ID"></el-table-column>
      <el-table-column prop="phone" label="手机号码"></el-table-column>
      <el-table-column prop="userId" class="selectColumn">
        <template slot-scope="scope">
          <el-button
            :class="{select,isSelect: scope.row.isSelect}"
            type="primary"
            @click="select($event,scope.row)"
            :disabled="scope.row.isSelect"
          >选择</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div slot="footer" class="dialog-footer btn">
      <el-button class="certain" type="primary" @click="certain">确定</el-button>
      <el-button class="cancel" type="primary" @click="cancel">取消</el-button>
    </div>
  </div>
</template>

<script>
import { setExpert } from "../../network/request/getmReport";

export default {
  data() {
    return {
      collegeList: [
        { id: 1, name: "计算机科学与工程学院" },
        { id: 2, name: "政法学院" },
        { id: 3, name: "电子信息与电气工程学院" },
        { id: 4, name: "地理与旅游学院" },
        { id: 5, name: "数学与大数据学院" },
        { id: 6, name: "化学与材料工程学院" },
        { id: 7, name: "建筑与土木工程学院" },
        { id: 8, name: "旭日广东服装学院" },
        { id: 9, name: "生命科学学院" },
        { id: 10, name: "经济管理学院" },
        { id: 11, name: "体育学院" }
      ],
      selectId: ""
    };
  },
  props: {
    isShow: {
      type: Boolean,
      default() {
        return false;
      }
    },
    expertData: {
      type: Array,
      default() {
        return [];
      }
    },
    setExpertPid: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  methods: {
    //获取所属二级学院名称
    GetCollegeName(row) {
      // console.log(row.collegeId);

      for (let i in this.collegeList) {
        if (row.collegeId === this.collegeList[i].id) {
          return this.collegeList[i].name;
        }
      }
    },
    certain() {
      console.log("expert:" + this.selectId);
      console.log("reportIds:" + this.setExpertPid);
      // console.log(Array.isArray(this.setExpertPid));

      setExpert({
        expert: this.selectId,
        reportIds: this.setExpertPid
        // expert: "expert1",
        // reportIds: ["7", "8"]
      }).then(res => {
        console.log(res);
      });

      // console.log(item);
      this.selectId = "";
      this.$emit("cancel");
    },
    cancel() {
      // console.log("取消");
      this.selectId = "";
      this.$emit("cancel");
    },
    select(e, row) {
      // console.log(this.setExpertPid);
      // console.log("--------");

      if (this.selectId.length >= 1) {
        alert("最多只能选择一位专家！");
      } else {
        // console.log(e.target);
        // console.log(row);
        row.isSelect = true;
        this.selectId = row.userId;
      }
      // console.log(this.selectId);
    }
  }
};
</script>
<style scoped>
.login-bg {
  z-index: 1 !important;
  display: none;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0px;
  left: 0px;
  background: rgba(0, 0, 0, 0.3);
}
#eTb {
  width: 850px;
  height: 400px;
  background-color: #fff;
  z-index: 9999;
}
.e-table {
  padding: 40px;
  padding-bottom: 40px;
}
.btn {
  width: 850px;
  padding-left: 630px;
}
.cancel {
  background-color: #fff;
  color: rgb(64, 158, 255);
}
.el-table::before {
  width: 0 !important;
}
.isSelect {
  /* color: #fff !important; */
  background-color: #fff;
  color: rgb(64, 158, 255);
}
</style>


