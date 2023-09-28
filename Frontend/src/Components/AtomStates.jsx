import { atom } from "jotai";

const today = new Date().toISOString().substring(0, 10);

const state = {
  modalShow: atom(false),
  user: atom(null),
  isLoggedIn: atom(false),
  showMyProfile: atom(false),
  // customDay: atom(null),
  displayCustomDay: atom(today),

  showMyRestaurants : atom(false),
  refreshShowRestaurant : atom(0),

  tagsModal : atom(false)
};
export default state;
