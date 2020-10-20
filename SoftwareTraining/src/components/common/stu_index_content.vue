<template>
  <section>
    <el-row>
      <el-col :span="21" :offset="4" class="content-view">
        <!--表格-->
        <el-table border :data="tableData" style="width: 100%">
          <template>
            <el-table-column type="selection"></el-table-column>
            <el-table-column label="项目名称" width="150" prop="projectName"></el-table-column>
            <el-table-column label="项目负责人" width="130" prop="userId"></el-table-column>
            <el-table-column label="指导老师1" width="120">
              <template slot-scope="scope">
                <span v-if="scope.row.oneId !== null">{{scope.row.oneId}}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
            <el-table-column label="指导老师2" width="120">
              <template slot-scope="scope">
                <span v-if="scope.row.twoId !== null">{{scope.row.twoId}}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
            <el-table-column label="项目等级" width="100">
              <template slot-scope="scope">
                <span v-if="scope.row.grade === 1">校级</span>
                <span v-if="scope.row.grade === 2">省级</span>
                <span v-if="scope.row.grade === 3">国家级</span>
                <span v-if="scope.row.grade === 0">无</span>
              </template>
            </el-table-column>
            <el-table-column label="立案申请" width="100">
              <template slot-scope="scope">
                <span v-if="scope.row.sreport === 1">已提交</span>
                <span v-else>未提交</span>
              </template>
            </el-table-column>
            <el-table-column label="中期报告" width="100">
              <template slot-scope="scope">
                <span v-if="scope.row.mreport == 1">已提交</span>
                <span v-else>未提交</span>
              </template>
            </el-table-column>
            <el-table-column label="结题报告" width="100">
              <template slot-scope="scope">
                <span v-if="scope.row.freport == 1">已提交</span>
                <span v-else>未提交</span>
              </template>
            </el-table-column>
          </template>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="primary"
                size="small"
                @click="handleMange(scope.$index, scope.row)"
              >管理</el-button>
              <el-button
                type="danger"
                size="small"
                @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination layout="prev, pager, next, jumper" :total="100"></el-pagination>
        </div>
      </el-col>
    </el-row>
    <el-dialog size="tiny" :model="dialogFormVisible" :visible.sync="dialogFormVisible">
      <el-form ref="tableData" :data="tableData" label-width="80px" class="project_form">
        <el-form-item label="项目名称">
          <el-input prop="tableData.projectName"></el-input>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="tableData.userId" style="width: 100%;"></el-input>
        </el-form-item>
        <el-form-item label="指导老师">
          <el-input v-model="tableData.oneId"></el-input>
        </el-form-item>
        <el-form-item label="项目文件" label-width="85px">
          <el-upload class="avatar-uploader" :action="baseUrl">
            <i class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">修改</el-button>
          <el-button type="danger" @click="dialogFormVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </section>
</template>

<script>
import { request } from "../../network/request/request";

export default {
  data() {
    return {
      form: {
        projectName: "111",
        projectMember: "123,12312,123",
        projectTeacher: "hasdd"
      },
      tableData: [],
      userId: "",
      dialogFormVisible: false,
      baseUrl: ""
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      this.userId = localStorage.getItem("USERID");
      request({
        url: "http://47.113.80.250:9003/project/select/" + this.userId,
        method: "get"
      }).then(res => {
        this.tableData = [];
        // console.log(res);
        this.tableData.push(res.data[0]);
        return this.tableData[0];
      });
    },
    handleMange(index, row) {
      // console.log(index);
      // console.log(row);

      this.dialogFormVisible = true;
    }
  }
};
</script>

<style>
.el-pagination {
  text-align: center;
  margin-top: 30px;
}
.el-message-box__btns .cancel {
  float: right;
  margin-left: 10px;
}
.content-view {
  position: fixed;
  top: 80px;
}
.project_form {
  margin-top: 0px;
  margin-left: 0px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>
