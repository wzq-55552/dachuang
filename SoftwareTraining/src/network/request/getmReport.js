import { request3 } from "./request"

export function getmReport() {
  return request3({
    url: "/project/select/" + localStorage.getItem('USERID')
  })
}

export function findmReport(data) {
  return request3({
    url: "/report/select",
    data,
    method: 'post'
  })
}


export function getAllExpert() {
  return request3({
    url: "/report/admin/select/expert/all"
  })
}

export function setExpert(data) {
  return request3({
    url: "/report/admin/set/expert",
    data,
    method: 'post'
  })
}

