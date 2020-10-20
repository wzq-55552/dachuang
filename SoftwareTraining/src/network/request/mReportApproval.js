import { request3 } from "./request"

export function mReportApproval(user, data) {
  return request3({
    url: "/report/" + user + "/approval",
    method: 'post',
    data
  })
}
