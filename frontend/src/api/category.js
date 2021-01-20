import request from "@/utils/request";

export function getAll() {
  return request({
    url: "guest/category/getAll",
    method: "get"
  });
}

export function getTopCateogry() {
  return request({
    url: "guest/category/getTopCatgory",
    method: "get"
  });
}

export function findById(id) {
  return request({
    url: "guest/category/findById/" + id,
    method: "get"
  });
}

export function deleteCategory(id) {
  return request({
    url: "admin/category/delete/" + id,
    method: "post"
  });
}

export function save(category) {
  return request({
    url: "admin/category/save",
    method: "post",
    data: category
  });
}
