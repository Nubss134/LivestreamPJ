import Vue from "vue";
import Router from "vue-router";
import Home from "@/views/Home";
import Category from "@/views/Category";
import ListCategory from "@/views/ListCategory";
import Lives from "@/views/Lives";
import Livestream from "@/views/Livestream";
import ReplyVideo from "@/views/ReplyVideo";
import Search from "@/views/Search";
import Member from "@/views/Member";

import ManageMember from "@/views/ManageMember";
import ManageCategory from "@/views/ManageCategory";
import ManageReport from "@/views/ManageReport";

import Chat from "@/components/Chat";
import MyInfo from "@/components/MyInfo";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home
    },
    {
      path: "/category/:categoryId",
      name: "Category",
      component: Category
    },
    {
      path: "/live/:streamerId",
      name: "Livestream",
      component: Livestream
    },
    {
      path: "/reply/:videoId",
      name: "ReplyVideo",
      component: ReplyVideo
    },
    {
      path: "/my-info",
      name: "MyInfo",
      component: MyInfo
    },
    {
      path: "/list-category",
      name: "ListCategory",
      component: ListCategory
    },
    {
      path: "/lives",
      name: "Lives",
      component: Lives
    },
    {
      path: "/chat",
      name: "Chat",
      component: Chat
    },
    {
      path: "/search",
      name: "Search",
      component: Search
    },
    {
      path: "/member/:memberId",
      name: "Member",
      component: Member
    },
    {
      path: "/video/:videoId",
      name: "ReplyVideo",
      component: ReplyVideo
    },
    {
      path: "/admin/manage-member",
      name: "ManageMember",
      component: ManageMember
    },
    {
      path: "/admin/manage-category",
      name: "ManageCategory",
      component: ManageCategory
    },
    {
      path: "/admin/manage-report",
      name: "ManageReport",
      component: ManageReport
    }
  ]
});
