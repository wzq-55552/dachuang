(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5a1a8b64"],{5620:function(e,t,a){"use strict";a.d(t,"a",(function(){return o})),a.d(t,"b",(function(){return l})),a.d(t,"c",(function(){return n}));var r=a("0b22");function o(e){return Object(r["b"])({url:"/report/select",data:e,method:"post"})}function l(){return Object(r["b"])({url:"/report/admin/select/expert/all"})}function n(e){return Object(r["b"])({url:"/report/admin/set/expert",data:e,method:"post"})}},"68e9":function(e,t,a){"use strict";var r=a("8979"),o=a.n(r);o.a},8979:function(e,t,a){},e4f4:function(e,t,a){"use strict";a.d(t,"a",(function(){return o}));var r=a("0b22");function o(e,t){return Object(r["b"])({url:"/report/"+e+"/approval",method:"post",data:t})}},e9ff:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"content-view"},[a("el-table",{staticStyle:{width:"100%"},attrs:{border:"",data:e.tableData},on:{"expand-change":e.handleMange}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form",{staticClass:"table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"项目ID:"}},[a("span",[e._v(e._s(t.row.projectId))])]),a("el-form-item",{attrs:{label:"项目名称:"}},[a("span",[e._v(e._s(t.row.projectName))])]),a("el-form-item",{attrs:{label:"负责人:"}},[a("span",[e._v(e._s(e.userInfo.userName))])]),a("el-form-item",{attrs:{label:"学号:"}},[a("span",[e._v(e._s(t.row.userId))])]),a("el-form-item",{attrs:{label:"联系方式:"}},[a("span",[e._v(e._s(e.userInfo.userPhone))])]),a("el-form-item",{attrs:{label:"项目等级:"}},[1===t.row.grade?a("span",[e._v("校级")]):e._e(),2===t.row.grade?a("span",[e._v("省级")]):e._e(),3===t.row.grade?a("span",[e._v("国家级")]):e._e(),0===t.row.grade?a("span",[e._v("无")]):e._e()]),a("el-form-item",{attrs:{label:"指导老师1:"}},[null!==t.row.oneId?a("span",[e._v(e._s(t.row.oneId))]):a("span",[e._v("无")])]),a("el-form-item",{attrs:{label:"指导老师2:"}},[null!==t.row.twoId?a("span",[e._v(e._s(t.row.twoId))]):a("span",[e._v("无")])]),a("el-form-item",{attrs:{label:"立案申请:"}},[1==t.row.sreport?a("span",{staticStyle:{color:"green"}},[e._v("已提交")]):a("span",{staticStyle:{color:"red"}},[e._v("未提交")])]),a("el-form-item",{attrs:{label:"中期报告:"}},[1==t.row.mreport?a("span",{staticStyle:{color:"green"}},[e._v("已提交")]):a("span",{staticStyle:{color:"red"}},[e._v("未提交")])]),a("el-form-item",{attrs:{label:"结案报告:"}},[1==t.row.freport?a("span",{staticStyle:{color:"green"}},[e._v("已提交")]):a("span",{staticStyle:{color:"red"}},[e._v("未提交")])])],1)]}}])}),[a("el-table-column",{attrs:{label:"项目名称",width:"240",prop:"projectName"}}),a("el-table-column",{attrs:{label:"项目负责人学号",width:"170",prop:"userId"}}),a("el-table-column",{attrs:{label:"所属学院",width:"150",prop:"collegeId",formatter:e.GetCollegeName}}),a("el-table-column",{attrs:{label:"项目等级",width:"100",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[1===t.row.grade?a("span",[e._v("校级")]):e._e(),2===t.row.grade?a("span",[e._v("省级")]):e._e(),3===t.row.grade?a("span",[e._v("国家级")]):e._e(),0===t.row.grade?a("span",[e._v("无")]):e._e()]}}])}),a("el-table-column",{attrs:{label:"中期报告",width:"100",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[1==t.row.mreport?a("span",{staticStyle:{color:"green"}},[e._v("已提交")]):a("span",{staticStyle:{color:"red"}},[e._v("未提交")])]}}])})],a("el-table-column",{attrs:{label:"中期报告操作",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.mReport(t.$index,t.row)}}},[e._v("查看")]),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return e.mReportApproval(t.$index,t.row)}}},[e._v("审核")])]}}])})],2),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":5,layout:"prev, pager, next, jumper",total:e.count},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t}}})],1),a("el-dialog",{staticClass:"midReport",attrs:{visible:e.dialogFormVisibleNew,title:"审核中期报告"},on:{"update:visible":function(t){e.dialogFormVisibleNew=t}},model:{value:e.dialogFormVisibleNew,callback:function(t){e.dialogFormVisibleNew=t},expression:"dialogFormVisibleNew"}},[a("el-form",{staticClass:"mReportForm"},[a("el-form-item",{attrs:{label:"项目名称:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReportNew.projectName))])]),a("el-form-item",{attrs:{label:"项目编号:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReportNew.projectId))])]),a("el-form-item",{attrs:{label:"学生学号","label-width":"100px"}},[a("span",[e._v(e._s(e.midReportNew.userId))])]),a("el-form-item",{attrs:{label:"评语","label-width":"100px"}},[a("el-input",{model:{value:e.comment,callback:function(t){e.comment=t},expression:"comment"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:e.pass}},[e._v("通过")]),a("el-button",{attrs:{type:"primary"},on:{click:e.noPass}},[e._v("不通过")]),a("el-button",{attrs:{type:"primary"},on:{click:e.Back}},[e._v("暂缓通过")])],1)],1),a("el-dialog",{staticClass:"midReport",attrs:{visible:e.dialogFormVisible,title:"中期报告详情"},on:{"update:visible":function(t){e.dialogFormVisible=t}},model:{value:e.dialogFormVisible,callback:function(t){e.dialogFormVisible=t},expression:"dialogFormVisible"}},[[a("el-form",{staticClass:"mReportForm",attrs:{model:e.midReport}},[a("el-form-item",{attrs:{label:"项目名称:","label-width":"100px"}},[a("span",[e._v(e._s(e.projectName))])]),a("el-form-item",{attrs:{label:"中期报告简介:","label-width":"100px"}},[0==e.changeContentValue?a("span",[a("span",[e._v(e._s(e.content))])]):a("el-form",{staticClass:"contentForm"},[a("el-input",{model:{value:e.content,callback:function(t){e.content=t},expression:"content"}}),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.changeContent}},[e._v("确定")])],1)],1),a("el-form-item",{staticClass:"midDisscuss",attrs:{label:"导师评议:","label-width":"100px"}},[0===e.midReport.tapproval?a("span",[e._v("未审核")]):e._e(),1===e.midReport.tapproval?a("span",{staticStyle:{color:"red"}},[e._v("不通过")]):e._e(),2===e.midReport.tapproval?a("span",{staticStyle:{color:"green"}},[e._v("已通过")]):e._e(),3===e.midReport.tapproval?a("span",{staticStyle:{color:"orange"}},[e._v("退回修改")]):e._e()]),a("el-form-item",{staticClass:"midDisscuss",attrs:{label:"学院评议:","label-width":"100px"}},[0===e.midReport.capproval?a("span",[e._v("未审核")]):e._e(),1===e.midReport.capproval?a("span",{staticStyle:{color:"red"}},[e._v("不通过")]):e._e(),2===e.midReport.capproval?a("span",{staticStyle:{color:"green"}},[e._v("已通过")]):e._e(),3===e.midReport.capproval?a("span",{staticStyle:{color:"blue"}},[e._v("退回学生")]):e._e(),4===e.midReport.capproval?a("span",{staticStyle:{color:"orange"}},[e._v("退回导师")]):e._e()]),a("el-form-item",{staticClass:"midDisscuss",attrs:{label:"大创管理评议:","label-width":"100px"}},[0===e.midReport.sapproval?a("span",[e._v("未审核")]):e._e(),1===e.midReport.sapproval?a("span",{staticStyle:{color:"red"}},[e._v("不通过")]):e._e(),2===e.midReport.sapproval?a("span",{staticStyle:{color:"green"}},[e._v("已通过")]):e._e(),3===e.midReport.sapproval?a("span",{staticStyle:{color:"orange"}},[e._v("退回修改")]):e._e()]),a("el-form-item",{attrs:{label:"评审专家:","label-width":"100px"}},[e.midReport.expertName?a("span",[e._v(e._s(e.midReport.expertName))]):a("span",[e._v("无")])]),a("el-form-item",{staticClass:"midDisscuss",attrs:{label:"评审专家评议:","label-width":"100px"}},[0===e.midReport.eapproval?a("span",[e._v("未审核")]):e._e(),1===e.midReport.eapproval?a("span",{staticStyle:{color:"red"}},[e._v("不通过")]):e._e(),2===e.midReport.eapproval?a("span",{staticStyle:{color:"green"}},[e._v("已通过")]):e._e(),3===e.midReport.eapproval?a("span",{staticStyle:{color:"orange"}},[e._v("暂缓通过")]):e._e()]),a("el-form-item",{attrs:{label:"导师评语:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReport.tcomment))])]),a("el-form-item",{attrs:{label:"学院评语:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReport.ccomment))])]),a("el-form-item",{attrs:{label:"大创管理评语:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReport.scomment))])]),a("el-form-item",{attrs:{label:"评审专家评语:","label-width":"100px"}},[a("span",[e._v(e._s(e.midReport.ecomment))])]),a("el-form-item",{staticStyle:{width:"90%"},attrs:{label:"文件列表:","label-width":"100px"}},[a("el-table",{staticStyle:{width:"100%","font-size":"12px"},attrs:{data:e.mfiles,height:"250",border:""}},[a("el-table-column",{attrs:{prop:"fname",label:"文件名",width:"210"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-link",{attrs:{tag:"a",target:"_blank",href:"http://47.113.80.250:8012/onlinePreview?url="+t.row.furl}},[a("el-button",{attrs:{size:"mini",type:"primary"}},[e._v("预览")])],1),a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary",size:"mini"},on:{click:function(a){return e.download(t.row)}}},[e._v("下载")])]}}])})],1)],1)],1)],a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("确定")])],1)],2)],1)},o=[],l=(a("4160"),a("b0c0"),a("d3b7"),a("3ca3"),a("159b"),a("ddb0"),a("2b3d"),a("0b22")),n=(a("5620"),a("e4f4")),i={data:function(){return{tableData:[],userId:"",dialogFormVisible:!1,dialogFormVisibleNew:!1,baseUrl:"",userInfo:[],count:0,limit:5,currentPage:1,midReport:[],midReportNew:[],projectName:"",mfiles:[],content:"",changeContentValue:!1,fileShow:!1,mfileNewName:"",mfileForm:new FormData,comment:"",row:{},collegeList:[{id:1,name:"计算机科学与工程学院"},{id:2,name:"政法学院"},{id:3,name:"电子信息与电气工程学院"},{id:4,name:"地理与旅游学院"},{id:5,name:"数学与大数据学院"},{id:6,name:"化学与材料工程学院"},{id:7,name:"建筑与土木工程学院"},{id:8,name:"旭日广东服装学院"},{id:9,name:"生命科学学院"},{id:10,name:"经济管理学院"},{id:11,name:"体育学院"}],reportId:0}},created:function(){this.initData()},methods:{initData:function(){var e=this;this.userId=localStorage.getItem("USERID"),Object(l["a"])({url:"http://47.113.80.250:9003/project/select/"+this.userId,method:"get"}).then((function(t){return e.tableData=[],console.log(t),t.data.forEach((function(t){e.tableData.push(t)})),e.total=e.tableData.length,e.tempList=e.tableData,e.tableData}))},handleMange:function(e,t){var a=this;Object(l["a"])({url:"http://47.113.80.250:9003/user/select/"+t[0].userId,method:"get"}).then((function(e){a.userInfo=[],a.userInfo=t,console.log(e),a.userInfo.userCollege=e.data.college.collegeName,a.userInfo.userName=e.data.user.userName,a.userInfo.userPhone=e.data.user.phone}))},handleSizeChange:function(e){this.pageSize=e,this.handleCurrentChange(this.currentPage)},handleCurrentChange:function(e){this.currentPage1=e,this.currentChangePage(this.tableData,e),console.log(this.tableData)},currentChangePage:function(e,t){var a=(t-1)*this.pageSize,r=t*this.pageSize;for(this.tempList=[];a<r;a++)e[a]&&this.tempList.push(e[a])},GetCollegeName:function(e){for(var t in console.log(e.collegeId),this.collegeList)if(e.collegeId===this.collegeList[t].id)return this.collegeList[t].name},mReport:function(e,t){var a=this;Object(l["a"])({url:"http://47.113.80.250:9003/report/select/"+t.projectId,method:"get"}).then((function(e){if(console.log(e),200===e.code)return a.projectName=t.projectName,a.midReport=e.data.mreport,a.mfiles=e.data.mfiles,a.content=e.data.mreport.content,a.midReport.expertName=e.data.expertName,console.log(a),e.code;alert(e.message)})).then((function(e){200===e&&(a.dialogFormVisible=!0)}))},mReportApproval:function(e,t){var a=this;this.row=t,console.log(t),1===t.mreport?(Object(l["a"])({url:"http://47.113.80.250:9003/report/select/"+t.projectId,method:"get"}).then((function(e){a.reportId=e.data.mreport.reportId})),this.midReportNew.projectId=t.projectId,this.midReportNew.projectName=t.projectName,this.midReportNew.userId=t.userId,this.dialogFormVisibleNew=!0):0===t.mreport&&alert("该报告未提交，无法审核！")},pass:function(){var e=this;Object(n["a"])("expert",{approval:2,comment:this.comment,reportId:this.reportId}).then((function(t){e.comment="",e.dialogFormVisibleNew=!1}))},noPass:function(){var e=this;Object(n["a"])("expert",{approval:1,comment:this.comment,reportId:this.reportId}).then((function(t){e.comment="",e.dialogFormVisibleNew=!1}))},Back:function(){var e=this;Object(n["a"])("expert",{approval:3,comment:this.comment,reportId:this.reportId}).then((function(t){e.comment="",e.dialogFormVisibleNew=!1}))},download:function(e){Object(l["a"])({url:"http://47.113.80.250:9002/download",data:{fileUrl:e.furl,fileName:e.fname},method:"POST",responseType:"blob"}).then((function(t){var a=t,r=new Blob([a]),o=e.fname,l=document.createElement("a");l.download=o,l.href=URL.createObjectURL(r),document.body.appendChild(l),l.click(),URL.revokeObjectURL(l.href),document.body.removeChild(l)}))}}},s=i,c=(a("68e9"),a("2877")),p=Object(c["a"])(s,r,o,!1,null,"6585d220",null);t["default"]=p.exports}}]);
//# sourceMappingURL=chunk-5a1a8b64.fb319139.js.map