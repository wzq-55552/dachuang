<template>
  <div class="content-view">
    <!--表格-->
    <el-table border :data="tableData" style="width: 100%" @expand-change="handleMange">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="项目ID:">
              <span>{{ props.row.projectId }}</span>
            </el-form-item>
            <el-form-item label="项目名称:">
              <span>{{ props.row.projectName }}</span>
            </el-form-item>
            <el-form-item label="负责人:">
              <span>{{ userInfo.userName }}</span>
            </el-form-item>
            <el-form-item label="学号:">
              <span>{{ props.row.userId }}</span>
            </el-form-item>
            <el-form-item label="联系方式:">
              <span>{{ userInfo.userPhone }}</span>
            </el-form-item>
            <el-form-item label="项目等级:">
              <span v-if="props.row.grade === 1">校级</span>
              <span v-if="props.row.grade === 2">省级</span>
              <span v-if="props.row.grade === 3">国家级</span>
              <span v-if="props.row.grade === 0">无</span>
            </el-form-item>
            <el-form-item label="指导老师1:">
              <span v-if="props.row.oneId !== null">{{props.row.oneId}}</span>
              <span v-else>无</span>
            </el-form-item>
            <el-form-item label="指导老师2:">
              <span v-if="props.row.twoId !== null">{{props.row.twoId}}</span>
              <span v-else>无</span>
            </el-form-item>
            <el-form-item label="立案申请:">
              <span v-if="props.row.sreport == 1" style="color:green">已提交</span>
              <span v-else style="color:red">未提交</span>
            </el-form-item>
            <el-form-item label="中期报告:">
              <span v-if="props.row.mreport == 1" style="color:green">已提交</span>
              <span v-else style="color:red">未提交</span>
            </el-form-item>
            <el-form-item label="结案报告:">
              <span v-if="props.row.freport == 1" style="color:green">已提交</span>
              <span v-else style="color:red">未提交</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <template>
        <el-table-column label="项目名称" width="240" prop="projectName"></el-table-column>
        <el-table-column label="项目负责人学号" width="170" prop="userId"></el-table-column>

        <el-table-column label="所属学院" width="150" prop="collegeId" :formatter="GetCollegeName"></el-table-column>

        <el-table-column label="项目等级" width="100" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.grade === 1">校级</span>
            <span v-if="scope.row.grade === 2">省级</span>
            <span v-if="scope.row.grade === 3">国家级</span>
            <span v-if="scope.row.grade === 0">无</span>
          </template>
        </el-table-column>
        <el-table-column label="中期报告" width="100" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.mreport == 1" style="color:green">已提交</span>
            <span v-else style="color:red">未提交</span>
          </template>
        </el-table-column>
      </template>
      <el-table-column label="中期报告操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="mReport(scope.$index, scope.row)">查看</el-button>
          <el-button
            type="primary"
            size="small"
            @click="mReportApproval(scope.$index, scope.row)"
          >审核</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="5"
        layout="prev, pager, next, jumper"
        :total="count"
      ></el-pagination>
    </div>

    <el-dialog
      v-model="dialogFormVisibleNew"
      :visible.sync="dialogFormVisibleNew"
      class="midReport"
      title="审核中期报告"
    >
      <el-form class="mReportForm">
        <el-form-item label="项目名称:" label-width="100px">
          <span>{{midReportNew.projectName}}</span>
        </el-form-item>
        <el-form-item label="项目编号:" label-width="100px">
          <span>{{midReportNew.projectId}}</span>
        </el-form-item>
        <el-form-item label="学生学号" label-width="100px">
          <span>{{midReportNew.userId}}</span>
        </el-form-item>
        <el-form-item label="评语" label-width="100px">
          <el-input v-model="comment"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="pass" type="primary">通过</el-button>
        <el-button @click="noPass" type="primary">不通过</el-button>
        <el-button @click="Back" type="primary">暂缓通过</el-button>
      </div>
    </el-dialog>
    <el-dialog
      v-model="dialogFormVisible"
      :visible.sync="dialogFormVisible"
      class="midReport"
      title="中期报告详情"
    >
      <template>
        <el-form class="mReportForm" :model="midReport">
          <el-form-item label="项目名称:" label-width="100px">
            <span>{{projectName}}</span>
          </el-form-item>
          <el-form-item label="中期报告简介:" label-width="100px">
            <span v-if="changeContentValue == false">
              <span>{{content}}</span>
            </span>
            <el-form class="contentForm" v-else>
              <el-input v-model="content"></el-input>
              <el-button type="primary" size="mini" @click="changeContent">确定</el-button>
            </el-form>
          </el-form-item>
          <el-form-item label="导师评议:" label-width="100px" class="midDisscuss">
            <span v-if="midReport.tapproval === 0">未审核</span>
            <span v-if="midReport.tapproval === 1" style="color:red">不通过</span>
            <span v-if="midReport.tapproval === 2" style="color:green">已通过</span>
            <span v-if="midReport.tapproval === 3" style="color:orange">退回修改</span>
          </el-form-item>
          <el-form-item label="学院评议:" label-width="100px" class="midDisscuss">
            <span v-if="midReport.capproval === 0">未审核</span>
            <span v-if="midReport.capproval === 1" style="color:red">不通过</span>
            <span v-if="midReport.capproval === 2" style="color:green">已通过</span>

            <span v-if="midReport.capproval === 3" style="color:blue">退回学生</span>
            <span v-if="midReport.capproval === 4" style="color:orange">退回导师</span>
          </el-form-item>
          <el-form-item label="大创管理评议:" label-width="100px" class="midDisscuss">
            <span v-if="midReport.sapproval === 0">未审核</span>
            <span v-if="midReport.sapproval === 1" style="color:red">不通过</span>
            <span v-if="midReport.sapproval === 2" style="color:green">已通过</span>
            <span v-if="midReport.sapproval === 3" style="color:orange">退回修改</span>
          </el-form-item>
          <el-form-item label="评审专家评议:" label-width="100px" class="midDisscuss">
            <span v-if="midReport.eapproval === 0">未审核</span>
            <span v-if="midReport.eapproval === 1" style="color:red">不通过</span>
            <span v-if="midReport.eapproval === 2" style="color:green">已通过</span>

            <span v-if="midReport.eapproval === 3" style="color:orange">暂缓通过</span>
          </el-form-item>
          <el-form-item label="导师评语:" label-width="100px">
            <span>{{midReport.tcomment}}</span>
          </el-form-item>
          <el-form-item label="学院评语:" label-width="100px">
            <span>{{midReport.ccomment}}</span>
          </el-form-item>
          <el-form-item label="大创管理评语:" label-width="100px">
            <span>{{midReport.scomment}}</span>
          </el-form-item>
          <el-form-item label="评审专家评语:" label-width="100px">
            <span>{{midReport.ecomment}}</span>
          </el-form-item>
          <el-form-item label="文件列表:" label-width="100px" style="width:90%">
            <el-table :data="mfiles" height="250" border style="width: 100%;font-size:12px">
              <el-table-column prop="fname" label="文件名" width="210"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-link
                    tag="a"
                    target="_blank"
                    :href="'http://47.113.80.250:8012/onlinePreview?url='+scope.row.furl"
                  >
                    <el-button size="mini" type="primary">预览</el-button>
                  </el-link>
                  <el-button type="primary" size="mini" 
                    style="margin-left:10px"
                    @click="download(scope.row)">
                      下载</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-form>
      </template>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { request } from "../../network/request/request";
import { getmReport } from "../../network/request/getmReport";
import { mReportApproval } from "../../network/request/mReportApproval";

export default {
  data() {
    return {
      tableData: [],
      userId: "",
      dialogFormVisible: false,
      dialogFormVisibleNew: false,
      baseUrl: "",
      userInfo: [],
      count: 0,
      limit: 5,
      currentPage: 1,
      midReport: [],
      midReportNew: [],
      projectName: "",
      mfiles: [],
      content: "",
      changeContentValue: false,
      fileShow: false,
      mfileNewName: "",
      mfileForm: new FormData(),
      //分隔
      comment: "",

      row: {},
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
      reportId: 0
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.userId = localStorage.getItem("USERID");
      request({
        url: "http://47.113.80.250:9003/project/select/" + this.userId,
        method: "get"
      }).then(res => {
        this.tableData = [];
        console.log(res);
        res.data.forEach(item => {
          this.tableData.push(item);
        });
        // console.log(this.tableData);

        this.total = this.tableData.length;
        this.tempList = this.tableData;
        return this.tableData;
      });
    },
    //点击拓展按钮显示拓展表格
    handleMange(index, row) {
      // console.log(index);
      // console.log(row);
      request({
        url: "http://47.113.80.250:9003/user/select/" + row[0].userId,
        method: "get"
      }).then(res => {
        this.userInfo = [];
        this.userInfo = row;
        console.log(res);
        this.userInfo.userCollege = res.data.college.collegeName;
        this.userInfo.userName = res.data.user.userName;
        this.userInfo.userPhone = res.data.user.phone;
        //将该项目中的数据整合到userInfo中
        // console.log(this.userInfo);
        // return this.tableData[0]
      });
    },
    //分页设置

    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.handleCurrentChange(this.currentPage);
    },
    handleCurrentChange(currentPage) {
      this.currentPage1 = currentPage;
      this.currentChangePage(this.tableData, currentPage);
      console.log(this.tableData);
    },
    currentChangePage(list, currentPage) {
      // console.log(list);
      // console.log(currentPage);

      let from = (currentPage - 1) * this.pageSize;
      let to = currentPage * this.pageSize;
      this.tempList = [];
      for (; from < to; from++) {
        if (list[from]) {
          this.tempList.push(list[from]);
        }
      }
      // console.log(this.tempList);
      // this.tableData = this.tempList
    },
    //获取所属二级学院名称
    GetCollegeName(row) {
      console.log(row.collegeId);

      for (let i in this.collegeList) {
        if (row.collegeId === this.collegeList[i].id) {
          return this.collegeList[i].name;
        }
      }
    },

    //中期报告信息获取
    mReport(index, row) {
      // console.log(row);
      request({
        url: "http://47.113.80.250:9003/report/select/" + row.projectId,
        method: "get"
      })
        .then(res => {
          console.log(res);
          if (res.code === 200) {
            this.projectName = row.projectName;
            this.midReport = res.data.mreport;
            this.mfiles = res.data.mfiles;
            this.content = res.data.mreport.content;
            return res.code;
          } else {
            alert(res.message);
          }
          // console.log(this.midReport);
        })
        .then(res => {
          // if(res.code == 200)
          if (res === 200) {
            this.dialogFormVisible = true;
          }
        });
    },
    //认可 中期报告
    mReportApproval(index, row) {
      // console.log("点击认可");
      // getmReport().then(res => {
      //   console.log(res);
      // });
      this.row = row;
      console.log(row);

      if (row.mreport === 1) {
        //操作
        request({
          url: "http://47.113.80.250:9003/report/select/" + row.projectId,
          method: "get"
        }).then(res => {
          this.reportId = res.data.mreport.reportId;
        });

        this.midReportNew.projectId = row.projectId;
        this.midReportNew.projectName = row.projectName;
        this.midReportNew.userId = row.userId;
        this.dialogFormVisibleNew = true;
        // console.log(row.projectId);

        // mReportApproval("teacher", {
        //   approval: 2,
        //   // comment: this.comment,
        //   comment: "老师认可测试",
        //   reportId: row.projectId
        // }).then(res => {
        //   console.log(res);
        // });
      } else if (row.mreport === 0) {
        alert("该报告未提交，无法审核！");
      }
    },
    pass() {
      mReportApproval("expert", {
        approval: 2,
        comment: this.comment,
        reportId: this.reportId
      }).then(res => {
        this.comment = "";
        // console.log(res);
        this.dialogFormVisibleNew = false;
      });
    },
    noPass() {
      mReportApproval("expert", {
        approval: 1,
        comment: this.comment,
        reportId: this.reportId
      }).then(res => {
        this.comment = "";
        // console.log(res);
        this.dialogFormVisibleNew = false;
      });
    },
    Back() {
      mReportApproval("expert", {
        approval: 3,
        comment: this.comment,
        reportId: this.reportId
      }).then(res => {
        this.comment = "";
        // console.log(res);
        this.dialogFormVisibleNew = false;
      });
    },
        //中期报告文件下载
    download(row) {
      request({
        url:'http://47.113.80.250:9002/download',
        data:{
          fileUrl:row.furl,
          fileName:row.fname
        },
        method:'POST'
      }).then(res => {
       const content = res;
        const blob = new Blob([content]);
        const fileName = row.fname; //下载文件名称
        const elink = document.createElement("a");
        elink.download = fileName;
        //  elink.style.display = 'none';
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        URL.revokeObjectURL(elink.href); // 释放URL 对象
        document.body.removeChild(elink);
      })
    }
  }
};
</script>

<style>
.table-expand {
  font-size: 0;
}

.table-expand label {
  width: 90px;
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  line-height: 20px;
  width: 50%;
}
.el-pagination {
  text-align: center;
  margin-left: -50px;
  margin-top: 30px;
}
.content-view {
  /* position: fixed; */
  /* margin-top: 80px; */
  padding-top: 100px;
  padding-left: 50px;
  overflow: auto;
}
.project_form {
  margin-top: 0px;
  margin-left: 0px;
}
.table-expand {
  margin: 0 auto;
}
.el-table__expanded-cell form {
  margin-top: -10px;
  width: 100%;
}
.table-expand el-button {
  margin: 0 auto;
}
.el-dialog__body {
  margin: -5px auto 0;
}
/* .el-form-item {
      margin-left: 15px;
    } */

.el-dialog__title {
  font-size: 16px;
  font-weight: 600;
  color: cadetblue;
}
.midReport {
  margin: 0 auto;
}
.mReportForm {
  margin: 0;
  margin-top: -18px;
  width: 100%;
}
.v-modal {
  position: relative;
}
.el-form-item {
  margin-bottom: 5px;
}
.el-form-item__label,
.el-form-item__content {
  line-height: 30px;
}
.midDisscuss {
  width: 50%;
  float: left;
}
.el-button {
  vertical-align: middle;
}
.changeContent {
  color: skyblue;
  font-size: 14px;
  margin-left: 10px;
  text-decoration: none;
}
.contentForm {
  margin: 0;
}
.contentForm .el-button {
  float: right;
  margin-top: 3px;
}
.el-col-20 {
  width: 74.1%;
}
</style>
