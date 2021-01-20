import request from "@/utils/request";

export function report(report) {
  return request({
    url: "guest/report/report",
    method: "post",
    data: report
  });
}

export function getListReport() {
  return request({
    url: "/admin/report/getAll",
    method: "get"
  });
}
export function getReport(id) {
  return request({
    url: "admin/report/" + id,
    method: "get"
  });
}
export function deleteReport(id) {
  return request({
    url: "admin/report/delete/" + id,
    method: "post"
  });
}
