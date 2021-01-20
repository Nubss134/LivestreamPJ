import request from "@/utils/request";

export function findByKeyWord(keyword) {
  return request({
    url: "guest/search/" + keyword,
    method: "get"
  });
}
