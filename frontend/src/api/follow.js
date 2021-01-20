import request from "@/utils/request";

export function isFollow(streamerId) {
  console.log("api", streamerId);
  return request({
    url: "user/follow/isFollow/" + streamerId,
    method: "get"
  });
}

export function follow(streamerId) {
  return request({
    url: "user/follow/follow/" + streamerId,
    method: "post"
  });
}

export function unFollow(streamerId) {
  return request({
    url: "user/follow/unFollow/" + streamerId,
    method: "post"
  });
}
