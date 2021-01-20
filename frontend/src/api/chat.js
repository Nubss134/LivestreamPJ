import request from "@/utils/request";

export function chat(chatData) {
  return request({
    url: "/user/chat",
    method: "post",
    data: chatData
  });
}
