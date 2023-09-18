import { validate } from "email-validator";
import { useAtom } from "jotai";
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

  wrongInput: (input) => {
    input.setCustomValidity("Invalid field.");
  },

  checkIfHolder: () => {
    const [user, setuser] = useAtom(state.user);
    const params = useParams();
    if (user) {
      console.log(user.restaurants);
      return user.restaurants?.reduce(
        (acc, cur) => (cur.id == params.restaurantId ? true : acc),
        false
      );
    }
    return false;
  },

  autoLogInWithToken: ({ setModalShow, setUser, setisLoggedIn }) => {
    fetch("http://localhost:8080/account/getUserWithToken", {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data)
        setUser(data);
        setisLoggedIn(true);
        setModalShow(false);
      })
      .catch((err) => {
        console.error(err);
      });
  },
};
