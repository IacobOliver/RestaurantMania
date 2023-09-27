import { validate } from "email-validator";

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
        setUser(data);
        setisLoggedIn(true);
        setModalShow(false);
      })
      .catch((err) => {
        console.error(err);
      });
  },

};

export let tags = []

export const getTag = () =>{
  fetch("http://localhost:8080/restaurantTag/getAll")
  .then(res => res.json())
  .then(data =>{
    console.log(data);
  }) 
}