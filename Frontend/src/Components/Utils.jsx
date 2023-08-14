import { validate } from "email-validator";
import { useAtom } from "jotai"
import state from "./AtomStates";
import { useParams } from "react-router-dom";



export const checking = {
  name: (e) => {
    if (e.target.value.length < 1) {
      e.target.setCustomValidity("Invalid field.");
    } else {
      e.target.setCustomValidity("");
    }
  },
  email: (e) => {
    if (validate(e.target.value)) {
      e.target.setCustomValidity("");
    } else {
      e.target.setCustomValidity("Invalid field.");
    }
  },
  password: (e) => {
    if (e.target.value.length < 7) {
      e.target.setCustomValidity("Invalid field.");
    } else {
      e.target.setCustomValidity("");
    }
  },

  userExist: (email) => {
    email.setCustomValidity("Invalid field.")
    // console.log("Email addres allready registered! Please try again!")
    alert("Email addres allready registered! Please try again!")
  },

  userNotExist: (email) => {
    email.setCustomValidity("Invalid field.")
  },

  wrongPass: (password) => {
    password.setCustomValidity("Invalid field.")
  },


  checkIfHolder: () => {
    const [user, setuser] = useAtom(state.user);
    const params = useParams();
    if (user) {
      return user.restaurants.reduce((acc, cur) => cur.id == params.restaurantId ? true : acc, false);
    }

    return false
  }
};

