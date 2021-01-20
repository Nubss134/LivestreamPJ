import request from "@/utils/request";

export function getHistory() {
  return request({
    url: "user/history/get",
    method: "get"
  });
}

export function saveHistory(videoId) {
  return request({
    url: "user/history/save/" + videoId,
    method: "post"
  });
}
