// eslint-disable-next-line no-undef
importScripts("https://www.gstatic.com/firebasejs/8.1.2/firebase-app.js");
// eslint-disable-next-line no-undef
importScripts("https://www.gstatic.com/firebasejs/8.1.2/firebase-messaging.js");

const firebaseConfig = {
  apiKey: "AIzaSyD-mr21OfXstPTBRphWvDuV0zj_4O44FSU",
  authDomain: "livestreampj-ab132.firebaseapp.com",
  databaseURL: "https://livestreampj-ab132.firebaseio.com",
  projectId: "livestreampj-ab132",
  storageBucket: "livestreampj-ab132.appspot.com",
  messagingSenderId: "87892589395",
  appId: "1:87892589395:web:0eb143b0729440e4317fa6",
  measurementId: "G-B1N9E8KWH2"
};

// eslint-disable-next-line no-undef
firebase.initializeApp(firebaseConfig);
// eslint-disable-next-line no-undef
const messaging = firebase.messaging();

messaging.onBackgroundMessage(function(payload) {
  console.log(
    "[firebase-messaging-sw.js] Received background message ",
    payload
  );
  // Customize notification here
  const notificationTitle = payload.data.title;
  const notificationOptions = {
    body: payload.data.content
  };

  self.registration.showNotification(notificationTitle, notificationOptions);
});
