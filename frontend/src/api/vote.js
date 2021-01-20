import request from "@/utils/request";

export function findById(id) {
  return request({
    url: "guest/vote/" + id,
    method: "get"
  });
}

export function openVote(data) {
  return request({
    url: "user/vote/openVote",
    method: "post",
    data: data
  });
}

export function closeVote() {
  return request({
    url: "user/vote/closeVote",
    method: "post"
  });
}

export function vote(params) {
  return request({
    url: "user/vote/vote",
    method: "post",
    params: params
  });
}

export function getVoteResult(voteId) {
  return request({
    url: "guest/vote/getResult/" + voteId,
    method: "get"
  });
}

export function isVote(voteId) {
  return request({
    url: "user/vote/isVote/" + voteId,
    method: "get"
  });
}
