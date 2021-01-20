import router from "./router";
import store from "./store";

router.beforeEach((to, from, next) => {
  if (to.fullPath.startsWith("/admin/")) {
    if (store.state.user.role === "ADMIN") {
      next();
    } else {
      next("/");
    }
  } else {
    next();
  }
});
