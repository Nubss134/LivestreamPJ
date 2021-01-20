import request from "@/utils/request";

export function create(data) {
  return request({
    url: "user/streamer/create",
    method: "post",
    data: data
  });
}

export function getInfo() {
  return request({
    url: "user/streamer/getInfo",
    method: "get"
  });
}

export function generateStreamKey() {
  return request({
    url: "user/streamer/generateStreamKey",
    method: "post"
  });
}
