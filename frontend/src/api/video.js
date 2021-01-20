import request from "@/utils/request";

export function getVideoInfo(videoId) {
  return request({
    url: "guest/video/getVideoInfo/" + videoId,
    method: "get"
  });
}

export function getAllLivestream() {
  return request({
    url: "guest/video/getAllLivestream",
    method: "get"
  });
}

export function getLivestreamByCategory(categoryId) {
  return request({
    url: "guest/video/getLivestream/" + categoryId,
    method: "get"
  });
}

export function getReplyVideo(streamerId) {
  return request({
    url: "guest/video/getReplyVideo/" + streamerId,
    method: "get"
  });
}

export function getLivestreamId(streamerId) {
  return request({
    url: "guest/video/getLivestreamId/" + streamerId,
    method: "get"
  });
}

export function getTopLiveByCategory() {
  return request({
    url: "guest/video/getTopLiveByCategory",
    method: "get"
  });
}

export function getBanner() {
  return request({
    url: "guest/video/getBanner",
    method: "get"
  });
}
