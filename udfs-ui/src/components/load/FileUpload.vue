<template>
  <div>
    <el-upload
    action="/file/upload"
    list-type="picture-card"
    ><i class="el-icon-plus"/></el-upload>
  </div>
</template>

<script>
    export default {
        name: "FileUpload"

    }
</script>

<style scoped>

</style>
