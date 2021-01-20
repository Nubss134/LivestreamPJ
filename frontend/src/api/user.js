import request from "@/utils/request";

export function login(data) {
  return request({
    url: "guest/login",
    method: "post",
    params: data
  });
}

export function signUp(data) {
  return request({
    url: "guest/signUp",
    method: "post",
    params: data
  });
}

export function isExistUsername(username) {
  return request({
    url: "guest/isExistUsername/" + username,
    method: "get"
  });
}

export function getPersonalInfo(id) {
  return request({
    url: "guest/member/getPersonalInfo/" + id,
    method: "get"
  });
}

export function updatePersonalInfo(params) {
  return request({
    url: "user/member/updatePersonalInfo",
    method: "post",
    params: params
  });
}

export function getFriends(memberId) {
  return request({
    url: "/user/member/getFriends/" + memberId,
    method: "get"
  });
}

export function logout() {
  return request({
    url: "/user/logout/",
    method: "post"
  });
}

export function getAllMember() {
  return request({
    url: "/admin/member/getAll",
    method: "get"
  });
}

export function deleteMember(memberId) {
  return request({
    url: "/admin/member/delete/" + memberId,
    method: "post"
  });
}

export function changeStatus(memberId) {
  return request({
    url: "/admin/member/changeStatus/" + memberId,
    method: "post"
  });
}

export function getListFollow() {
  return request({
    url: "/user/member/getListFollow",
    method: "get"
  });
}
