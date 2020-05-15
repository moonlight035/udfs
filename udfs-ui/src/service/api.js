import axios from 'axios'

export const request=(url,type='get',isJson='false',param)=>{
  const query = {
    url:url,
    method:type,
    headers:{}
  }
  if(type==='get')
    query.params=param
  if(type==='post') {
    if (isJson) {
      query.headers['Content-Type'] = 'application/json'
      query.data=param
    }else{
      query.headers['Content-Type'] = 'application/x-www-form-urlencoded'
      query.data=qs.stringify(param)
    }
  }
  return new Promise(((resolve, reject) => {
    axios.request(query).then(res=>{
      if(res.status===200)
        resolve(res.data)
      else
        this.$message.error(res.error)
    }).catch(e=>{
      this.$message.error(e)
    })
  }))
}
