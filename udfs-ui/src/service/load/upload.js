import {request} from '../api'
export default{
  queryFile : param=>request("/file/query",param),
  downloadFile : param=>request("file/download",param)
}
