<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="分区">
        <el-select v-model="query.partition" clearable>
          <el-option
            v-for="item in partitionOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="query.type" clearable>
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="query.name"></el-input>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col style="text-align: center;">
        <el-button size="small" @click="queryFile">查询</el-button>
        <el-button size="small" @click="openUploadDialog">上传</el-button>
      </el-col>
    </el-row>
    <el-table style="width: 100%" :data="tableDataList">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="video" label="视频自增编码"></el-table-column>
      <el-table-column prop="saveName" label="保存名字"></el-table-column>
      <el-table-column prop="name" label="上传名称"></el-table-column>
      <el-table-column prop="suffix" label="后缀"></el-table-column>
      <el-table-column prop="url" label="url"></el-table-column>
      <el-table-column prop="type" label="文件类型" :formatter="typeFormat"></el-table-column>
      <el-table-column prop="partition" label="分区" :formatter="partitionFormat"></el-table-column>
      <el-table-column prop="createAccount" label="创建人"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="downloadFile(scope.row)" type="text" size="small">下载</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="this.queryFile"
      @current-change="this.queryFile"
      :current-page="query.pageIndex"
      :page-sizes="[10, 20, 30, 50]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
    <el-dialog :visible.sync="uploadDialogVisible">
      <el-form :inline="true">
        <el-row>
          <el-col :offset="8" :span="5">
            <el-form-item label="所属分区">
              <el-select v-model="uploadParam.partition" style="width: 70%" clearable>
                <el-option
                  v-for="item in partitionOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-upload
              ref="upload"
              action="/file/upload"
              :data="uploadParam"
              :file-list="fileList"
              :on-success="uploadSuccess"
              :before-upload="beforeUpload"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import upload from '@/service/load/upload.js'
  export default {
    name: "FileUpload",
    data() {
      return {
        uploadDialogVisible: false,
        uploadParam: {
          partition: ''
        },
        partitionOptions: [
          {value: 1, label: '游戏'},
          {value: 2, label: '动漫'},
          {value: 3, label: '鬼畜'}
        ],
        typeOptions: [
          {value: 1, label: '视频'},
          {value: 2, label: '音频'},
          {value: 3, label: '图片'},
          {value: 4, label: '文档'},
          {value: 5, label: '压缩包'},
          {value: 6, label: '其它'},
        ],
        query: {
          partition: '',
          type: '',
          name: '',
          pageIndex:1,
          pageSize:20
        },
        fileList: [],
        total:0,
        tableDataList: [],
      }
    },
    methods: {
      downloadFile(row) {
        let query = {
          partition:row.partition,
          saveName:row.saveName,
          suffix:row.suffix
        }
        upload.downloadFile(query)
      },
      openUploadDialog(){
        this.uploadDialogVisible=true;
        this.$refs.upload.clearFiles();
      },
      beforeUpload(file){
        if(this.uploadParam.partition==='') {
          this.$message.warning("请选择分区")
          return false;
        }
      },
      uploadSuccess(response, file, fileList) {
        if (response.status != 200) {
          fileList.pop()
          this.$message.error(response.error)
        }
      },
      queryFile() {
        upload.queryFile(this.query).then(res=>{
          this.tableDataList = res.data.dataList;
          this.total = res.data.total
        })
      },
      typeFormat(row){
        let options = this.typeOptions
        for (let i = 0; i < options.length; i++) {
          if(options[i].value == row.type)
            return options[i].label
        }
      },
      partitionFormat(row){
        let options = this.partitionOptions
        for (let i = 0; i < options.length; i++) {
          if(options[i].value == row.partition)
            return options[i].label
        }
      }
    }
  }
</script>

<style scoped>

</style>
