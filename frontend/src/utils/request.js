import axios from "axios";
// import { Message } from 'element-ui'
// import store from '@/store'
import { getToken } from "@/utils/auth";

const BASE_API = "http://localhost:8081/api/v1";

// create an axios instance
const service = axios.create({
  baseURL: BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
});

// request interceptor
service.interceptors.request.use(
  config => {
    let isGuest = config.url.indexOf("guest") === 0;
    if (!isGuest) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers["Authorization"] = "Bearer " + getToken();
    }
    return config;
  },
  error => {
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    return response;
  },
  error => {
    if (error.response.status === 403) {
      // Message({
      //   message: 'Vui lòng đăng nhập',
      //   duration: 3 * 1000
      // })
    } else {
      // Message({
      //   message: 'Có lỗi xảy ra',
      //   type: 'error',
      //   duration: 3 * 1000
      // })
    }
    return Promise.reject(error);
  }
);

export default service;
