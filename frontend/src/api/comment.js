import request from "@/utils/request";

export function pushComment(videoId, data) {
  return request({
    url: "user/comment/" + videoId,
    method: "post",
    data: data
  });
}
